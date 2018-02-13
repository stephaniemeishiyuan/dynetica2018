/***************************************************************************
 *                      ReactiveSystem.java  -  description
 *                            -------------------
 *   begin                : Mon Mar 18 2000
 *   copyright            : (C) 2000 by Lingchong You
 *   email                : you@cae.wisc.edu
 *                                                                         
 ***************************************************************************/
package dynetica.system;

import dynetica.entity.Annotation;
import dynetica.entity.Parameter;
import dynetica.expression.Constant;
import dynetica.gui.visualization.NetworkLayout;
import dynetica.entity.*;
import dynetica.algorithm.*;
import dynetica.expression.FunctionExpression;
import dynetica.expression.GeneralExpression;
import dynetica.expression.SimpleOperator;
import dynetica.reaction.*;
import dynetica.gui.*;
import java.util.*;
import java.io.*;

import javax.swing.tree.*;
import javax.xml.stream.XMLStreamException;

import dynetica.reaction.Reaction;
import org.sbml.jsbml.*;

/**
 * The following fields are added 4/19/2005 --expresions --expressionNode
 */
public class ReactiveSystem extends SimpleSystem {
    // for easy access, substances, parameters, and reactions are
    // simutaneously stored in separate lists.
    protected List substances = new ArrayList();
    protected List parameters = new ArrayList();
    //
    // the List expressions is added 4/19/2005 by L. You
    //
    protected List expressions = new ArrayList();
    protected List progressiveReactions = new ArrayList();
    protected List equilibratedReactions = new ArrayList();

    // the networkLayout field is added 4/20/2005 by LY
    public NetworkLayout networkLayout = new NetworkLayout(null, this);

    // the field time is used to keep track of the time course of the
    // simulation. The major reason to make
    // <code> time </code> as a Timer (derived from Parameter)
    // is to account for situations where the concentrations of some
    // substances may be a function of time. In those cases, the user can
    // just put "Time" into rate expressions directly.
    //

    public SimulationTimer timer;

    // the field volume is treated as a Substance of the
    // system may change as a function of time (can be specified in
    // the input file).
    public Parameter volume;

    public Annotation systemInformation = new Annotation(this,
            "SystemInformation");

    Algorithm algorithm = null;

    /**
     * Holds value of property treeModel. This is used to store the entity names
     * in a tree model, which in turn will be used for displaying the system in
     * a tree-like structure (JTree is used) in class SystemEditor
     */
    protected javax.swing.tree.DefaultTreeModel treeModel;
    protected DefaultMutableTreeNode systemNode = null;
    protected DefaultMutableTreeNode reactionNode = null;
    protected DefaultMutableTreeNode substanceNode = null;
    protected DefaultMutableTreeNode parameterNode = null;
    protected DefaultMutableTreeNode progressiveNode = null;
    protected DefaultMutableTreeNode equilibratedNode = null;
    // added 4/19/2005
    protected DefaultMutableTreeNode expressionNode = null;

    /** Holds value of property graph. */
    private dynetica.gui.systems.SystemGraph graph;

    /**
     * Constructs a ReactiveSystem with a name "ReactiveSystem" and with a null
     * super system
     */
    public ReactiveSystem() {
        this("ReactiveSystem");
    }

    public ReactiveSystem(String name) {
        setName(name);
        initializeTreeModel();
        timer = new SimulationTimer("Time", this);
        timer.setVisible(false);
        setSaved(false);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        if (treeModel != null) {
            systemNode.setUserObject(name);
            // treeModel.reload(systemNode);
        }
    }

    protected void initializeTreeModel() {
        systemNode = new DefaultMutableTreeNode(getName());
        treeModel = new DefaultTreeModel(systemNode);
    }

    public SimulationTimer getTimer() {
        return timer;
    }

    /**
     * Get the value of volume.
     * 
     * @return Value of volume.
     */
    public double getVolume() {
        return volume.getValue();
    }

    /**
     * Set the value of volume.
     * 
     * @param v
     *            Value to assign to volume.
     */

    public void setVolume(double v) {
        volume.setValue(v);
    }

    /**
     * Get the value of algorithm.
     * 
     * @return Value of algorithm.
     */

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Set the value of algorithm.
     * 
     * @param v
     *            Value to assign to algorithm.
     */
    public void setAlgorithm(Algorithm v) {
        this.algorithm = v;
    }

    protected boolean insertEntityIntoTreeModel(Entity entity) {
        if (entity instanceof Substance) {
            //
            // the following if statement is added 4/19/05 by LY
            //

            // following if system modified by Kanishk Asthana on 23 July 2013
            // to take care of a runtime
            // error which was happening when an expression varialbe is the
            // first entity added to the system

            if (entity instanceof ExpressionVariable) {
                if (expressionNode == null) {
                    expressionNode = new DefaultMutableTreeNode("Expressions");
                    treeModel.insertNodeInto(expressionNode, systemNode,
                            Math.min(systemNode.getChildCount(), 2));
                }
                treeModel.insertNodeInto(
                        new DefaultMutableTreeNode(entity.getName()),
                        expressionNode, expressionNode.getChildCount());
                return true;
            } else if (substanceNode == null) {
                substanceNode = new DefaultMutableTreeNode("Substances");
                treeModel.insertNodeInto(substanceNode, systemNode,
                        Math.min(systemNode.getChildCount(), 0));
            }
            treeModel.insertNodeInto(
                    new DefaultMutableTreeNode(entity.getName()),
                    substanceNode, substanceNode.getChildCount());
            return true;
        }
        if (entity instanceof Reaction) {
            if (reactionNode == null) {
                reactionNode = new DefaultMutableTreeNode("Reactions");
                treeModel.insertNodeInto(reactionNode, systemNode,
                        Math.min(systemNode.getChildCount(), 3));
            }

            if (entity instanceof Progressive) {
                if (progressiveNode == null) {
                    progressiveNode = new DefaultMutableTreeNode(
                            "Progressive Reactions");
                    treeModel.insertNodeInto(progressiveNode, reactionNode, 0);
                }
                treeModel.insertNodeInto(
                        new DefaultMutableTreeNode(entity.getName()),
                        progressiveNode, progressiveNode.getChildCount());
                return true;
            }

            if (entity instanceof Equilibrated) {
                if (equilibratedNode == null) {
                    equilibratedNode = new DefaultMutableTreeNode(
                            "Equilibrated Reactions");
                    treeModel.insertNodeInto(equilibratedNode, reactionNode,
                            Math.min(reactionNode.getChildCount(), 1));
                }

                treeModel.insertNodeInto(
                        new DefaultMutableTreeNode(entity.getName()),
                        equilibratedNode, equilibratedNode.getChildCount());
                return true;
            }
        }

        if (entity instanceof Parameter) {
            if (parameterNode == null) {
                parameterNode = new DefaultMutableTreeNode("Parameters");
                treeModel.insertNodeInto(parameterNode, systemNode,
                        Math.min(systemNode.getChildCount(), 1));
            }
            treeModel.insertNodeInto(
                    new DefaultMutableTreeNode(entity.getName()),
                    parameterNode, parameterNode.getChildCount());
            return true;
        }

        else
            return false;
    }

    /**
     * Adds an Entity to this system. Also updates the underlying tree model for
     * the system.
     */

    @Override
    public void add(Entity entity) {
        if (!contains(entity.getName())) {
            entities.put(entity.getName(), entity);
            if (entity instanceof Substance) {
                substances.add(entity);
                // note that ExpressionVariable is a special class of Substance
                if (entity instanceof ExpressionVariable) {
                    expressions.add(entity);
                }
            }

            else if (entity instanceof Progressive) {
                progressiveReactions.add(entity);
            }

            else if (entity instanceof Equilibrated) {
                equilibratedReactions.add(entity);
            }

            else if (entity instanceof Parameter) {
                parameters.add(entity);
            }

            //
            // the following else if statement is added 4/20/2005
            else if (entity instanceof NetworkLayout) {
                networkLayout = (NetworkLayout) entity;
            } else if (entity instanceof Annotation) {
                systemInformation = (Annotation) entity;
            }
            insertEntityIntoTreeModel(entity);
            fireSystemStructureChange();
        }
    }

    // 4/20/2005 revised to handle removal of ExpressionVariables
    protected boolean removeEntityFromTreeModel(Entity entity) {
        Enumeration e;
        if (entity instanceof Substance) {
            if (entity instanceof ExpressionVariable)
                e = expressionNode.children();
            else
                e = substanceNode.children();
        }

        else if (entity instanceof Progressive) {
            e = progressiveNode.children();
        }

        else if (entity instanceof Equilibrated) {
            e = equilibratedNode.children();
        }

        else if (entity instanceof Parameter) {
            e = parameterNode.children();
        } else
            return false;

        DefaultMutableTreeNode node;
        while (e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            String nodeName = node.getUserObject().toString();
            if (nodeName.compareTo(entity.getName()) == 0) {
                TreeNode[] pathToRoot = treeModel.getPathToRoot(node);
                //
                // remove the node first
                //
                treeModel.removeNodeFromParent(node);

                //
                // following the path to the root, remove all nodes that doesn't
                // contain
                // a child. Reset the major nodes to null if necessary.
                //
                for (int i = pathToRoot.length - 2; i > 0; i--) {
                    if (pathToRoot[i].getChildCount() == 0) {
                        treeModel
                                .removeNodeFromParent((DefaultMutableTreeNode) pathToRoot[i]);
                        if (substanceNode == pathToRoot[i])
                            substanceNode = null;
                        else if (expressionNode == pathToRoot[i])
                            expressionNode = null;
                        else if (reactionNode == pathToRoot[i])
                            reactionNode = null;
                        else if (progressiveNode == pathToRoot[i])
                            progressiveNode = null;
                        else if (equilibratedNode == pathToRoot[i])
                            equilibratedNode = null;
                        else if (parameterNode == pathToRoot[i])
                            parameterNode = null;
                    } else
                        return true;
                }
                return true;
            }
        }
        return false;
    }

    //
    // Modified on 4/17/2001
    //
    // Modified on 8/22/2001
    // removes the Entity as well as updates the tree model.

    // 4/20/2005. Revised to handle removal of ExpressionVariables
    //

    @Override
    public void remove(String name) {
        // ToDo:
        // need to check dependence before removing stuff
        // from a system
        //
        Entity entity = (Entity) get(name);
        entities.remove(name);
        if (entity instanceof Substance) {
            substances.remove(entity);
            System.out.println("removing" + name);
            if (entity instanceof ExpressionVariable)
                expressions.remove(entity);
        }

        else if (entity instanceof Progressive) {
            progressiveReactions.remove(entity);
        }

        else if (entity instanceof Equilibrated) {
            equilibratedReactions.remove(entity);
        }

        else {
            parameters.remove(entity);
        }

        removeEntityFromTreeModel(entity);
        fireSystemStructureChange();
    }

    @Override
    public void rename(String oldName, String newName) {
        super.rename(oldName, newName);
        Entity entity = get(newName);
        Enumeration e;
        if (entity instanceof Substance) {
            if (entity instanceof ExpressionVariable)
                e = expressionNode.children();
            else
                e = substanceNode.children();
        }

        else if (entity instanceof Progressive) {
            e = progressiveNode.children();
        }

        else if (entity instanceof Equilibrated) {
            e = equilibratedNode.children();
        }

        else {
            e = parameterNode.children();
        }

        DefaultMutableTreeNode node;
        while (e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            String nodeName = node.getUserObject().toString();
            if (nodeName.compareTo(oldName) == 0) {
                node.setUserObject(newName);
                treeModel.reload(node);
                break;
            }
        }
        setSaved(false);
    }

    public void addSubstance(Substance substance) {
        add(substance);
    }

    public void addParameter(Parameter parameter) {
        add(parameter);
    }

    @Override
    public Parameter getParameter(String name) {
        return (Parameter) entities.get(name);
    }

    @Override
    public Substance getSubstance(String name) {
        return (Substance) entities.get(name);
    }

    public Reaction getReaction(String name) {
        return (Reaction) entities.get(name);
    }

    // 8/24/2000 (Lingchong)
    // The following methods may be redundant.
    // It is more efficient to access the fields directly.
    // Consider to set the fields to have default accessibility, because
    // right now the only places I can imagine that will need access to these
    // fields are ReactiveSystem (this class) and GeneticSystem (a Child of
    // ReactiveSystem. The fields can be set as protected as well.
    //
    public List getSubstances() {
        return substances;
    }

    public List getParameters() {
        return parameters;
    }

    public List getProgressiveReactions() {
        return progressiveReactions;
    }

    public List getExpressions() {
        return expressions;
    }

    public Object[] toArray() {
        return entities.values().toArray();
    }

    public Iterator eIterator() {
        return entities.values().iterator();
    }

    @Override
    public void reset() {
        Object[] entityArray = toArray();
        int n = entityArray.length;
        for (int i = 0; i < n; i++) {
            Entity e = (Entity) entityArray[i];
            e.reset();
            System.out.println("Resetting   " + e.toString());
        }
        System.out.println("Resetting done.");
    }

    /**
     * Converts the ReactiveSystem into a String for output. the format of this
     * output should be compatible with the input file of the system
     */
    @Override
    public String toString() {
        StringBuffer str = new StringBuffer(super.toString() + " {" + NEWLINE);
        str.append(NEWLINE);
        str.append(systemInformation.getCompleteInfo());
        str.append(NEWLINE);
        for (int i = 0; i < substances.size(); i++) {
            Substance s = ((Substance) substances.get(i));
            if (!(s instanceof ExpressionVariable))
                str.append(s.getCompleteInfo() + NEWLINE);
        }

        str.append(NEWLINE);
        for (int i = 0; i < parameters.size(); i++) {
            Parameter p = ((Parameter) parameters.get(i));
            str.append(p.getCompleteInfo() + NEWLINE);
        }

        str.append(NEWLINE);

        for (int i = 0; i < expressions.size(); i++) {
            ExpressionVariable ev = ((ExpressionVariable) expressions.get(i));
            str.append(ev.getCompleteInfo() + NEWLINE);
        }

        str.append(NEWLINE);

        for (int i = 0; i < progressiveReactions.size(); i++)
            str.append(progressiveReactions.get(i) + NEWLINE);

        for (int i = 0; i < equilibratedReactions.size(); i++)
            str.append(equilibratedReactions.get(i) + NEWLINE);

        str.append(networkLayout.getCompleteInfo() + NEWLINE);
        str.append("}" + NEWLINE);
        return str.toString();
    }

    public void updateEquilibratedReactions() {
        for (int i = 0; i < equilibratedReactions.size(); i++) {
            ((EquilibratedReaction) (equilibratedReactions.get(i))).update();
        }
    }

    //
    // Added 5/7/2006 by L. You to handle updating of expressions.
    //
    public void updateExpressions() {
        for (int i = 0; i < expressions.size(); i++)
            ((ExpressionVariable) (expressions.get(i))).update();
    }

    //
    // added 6/2016 by LY to handle resetting ore expressions.
    public void resetExpressions() {
        for (int i = 0; i < expressions.size(); i++)
            ((ExpressionVariable) (expressions.get(i))).reset();
    }  
    
    public void updateSpecialReactions(double dt) {
        updateEquilibratedReactions();
    }

    public Substance[] getSubstancesForOutput() {
        int ns = 0;
        for (int i = 0; i < substances.size(); i++)
            if (((Substance) substances.get(i)).isVisible())
                ns++;

        Substance[] data = new Substance[ns];
        int k = 0;
        for (int i = 0; i < substances.size(); i++) {
            Substance s = ((Substance) substances.get(i));
            if (s.isVisible()) {
                data[k] = s;
                k++;
            }
        }
        return data;
    }

    public void printTimeCourses(File output) {
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(output));
            Substance[] subs = getSubstancesForOutput();
            double[][] data = new double[subs.length + 1][timer.getTimePoints().length];
            data[0] = timer.getTimePoints();
            for (int i = 1; i < data.length; i++)
                data[i] = subs[i - 1].getValues();

            out.print("t");
            for (int i = 0; i < subs.length; i++) {
                out.print("  ");
                out.print(subs[i].getName());
            }
            out.println();
            for (int i = 0; i < data[0].length; i++) {
                for (int j = 0; j < data.length; j++)
                    out.print(data[j][i] + "  ");
                out.println();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List getEquilibratedReactions() {
        return equilibratedReactions;
    }

    @Override
    public javax.swing.JPanel editor() {
        return new dynetica.gui.systems.SystemEditor(this);
    }

    /**
     * Getter for property treeModel.
     * 
     * @return Value of property treeModel.
     */
    public javax.swing.tree.TreeModel getTreeModel() {
        return treeModel;
    }

    /**
     * Getter for property graph.
     * 
     * @return Value of property graph.
     */
    public dynetica.gui.systems.SystemGraph getGraph() {
        return graph;
    }

    /**
     * Setter for property graph.
     * 
     * @param graph
     *            New value of property graph.
     */
    public void setGraph(dynetica.gui.systems.SystemGraph graph) {
        this.graph = graph;
    }

    //
    // each model will be saved in a single file from version 1.2
    // 4/19/2005 L. You
    //
    @Override
    public void save() {
        if (!isSaved()) {
            if (fileType.equals("dyn"))
                super.save();
            else if (fileType.equals("xml"))
                saveAsSBML();
        }
    }

    private void saveAsSBML() {
        int level = 2;
        int version = 4;

        // Used below for adding parameters
        org.sbml.jsbml.Parameter param;

        SBMLDocument doc = new SBMLDocument(level, version);
        Model model = doc.createModel(name);

        Compartment compartment = model.createCompartment("default");
        compartment.setSize(1d);

        // Add the substances (in Dynetica) to the SBML model (as Species)
        Substance sub;
        Species spec;
        for (int i = 0; i < substances.size(); i++) {
            // Handles cases where a constant equation has been added by moving it to the list of parameters
            if(substances.get(i) instanceof dynetica.entity.ExpressionVariable && ( (ExpressionVariable) substances.get(i)).getExpression() instanceof dynetica.expression.Constant) {
                param = new org.sbml.jsbml.Parameter(( (ExpressionVariable) substances.get(i)).getName());

                param.setValue(( (ExpressionVariable) substances.get(i)).getExpression().getValue());

                model.addParameter(param);
            } else if(substances.get(i) instanceof dynetica.entity.Substance) {
                sub = (Substance) substances.get(i);

                spec = new Species(sub.getName());

                spec.setName(sub.getName());
                spec.setCompartment(compartment);

                // not sure if I should use setInitialAmount or
                // setInitialConcentration here - seems that it should be amount,
                // from the values, in some example models
                spec.setInitialAmount(sub.getInitialValue());

                model.addSpecies(spec);
            }
        }

        // Add the reactions
        org.sbml.jsbml.Reaction reaction;
        SpeciesReference specref;
        ModifierSpeciesReference modspecref;
        ProgressiveReaction currentReaction;
        List products, reactants, modifiers;
        GeneralExpression rateExpression;

        for (int i = 0; i < progressiveReactions.size(); i++) {
            currentReaction = (ProgressiveReaction) progressiveReactions.get(i);
            reaction = model.createReaction(currentReaction.getName());

            reactants = currentReaction.getReactants();
            modifiers = currentReaction.getCatalysts();
            products = currentReaction.getProducts();

            // Add reactants
            for (int j = 0; j < reactants.size(); j++) {
                specref = new SpeciesReference(
                        model.getSpecies(((Substance) reactants.get(j))
                                .getName()));
                reaction.addReactant(specref);
            }

            // Add modifiers/catalysts
            for (int j = 0; j < modifiers.size(); j++) {
                // Skip constants stored in Dynetica as expression variables
                // They were added as parameters in SBML
                if(!model.containsParameter(((Substance) modifiers.get(j)).getName())) {
                    // Screen for expression variables - they will always be classified as catalysts in Dynetica
                    modspecref = new ModifierSpeciesReference(
                            model.getSpecies(((Substance) modifiers.get(j))
                                    .getName()));

                    reaction.addModifier(modspecref);
                }
            }

            // Add products
            for (int j = 0; j < products.size(); j++) {
                specref = new SpeciesReference(
                        model.getSpecies(((Substance) products.get(j))
                                .getName()));
                reaction.addProduct(specref);
            }

            // Set as non-reversible
            reaction.setReversible(false);

            rateExpression = ((ProgressiveReaction) progressiveReactions.get(i))
                    .getRateExpression();

            // Approach: create an org.sbml.jsbml.KineticLaw, which inherits
            // from org.sbml.jsbml.AbstractMathContainer, which has a setMath
            // method. That needs to be passed an ASTNode object, which has left
            // and right children that are also ASTNode objects - just like with
            // GeneralExpression in Dynetica. Basically, need to traverse the
            // GeneralExpression binary tree and copy it over to the ASTNode
            // binary tree. rateExpression can be casted to
            // dynetica.expression.SimpleOperator:
            // ((dynetica.expression.SimpleOperator) rateExpression).getB()
            // This inherits from Expression, so you can use getA and getB
            KineticLaw kineticLaw = new KineticLaw();
            if(rateExpression instanceof Parameter || rateExpression instanceof Substance)
                kineticLaw.setMath(new ASTNode(rateExpression.toString()));
            else
                kineticLaw
                    .setMath(expressionToASTNode((SimpleOperator) rateExpression));

            // Add parameters
            for (Parameter dynParam : (ArrayList<Parameter>) currentReaction.getParameters()) {
                if(!model.containsParameter(dynParam.toString())) {
                    param = new org.sbml.jsbml.Parameter(dynParam.toString());

                    param.setValue(dynParam.getDefaultValue());

                    model.addParameter(param);
                }
            }

            reaction.setKineticLaw(kineticLaw);
        }

        // Add the expression variables
        AssignmentRule assignmentRule;
        ASTNode math;

        for (ExpressionVariable currentExpVar : (ArrayList<ExpressionVariable>) expressions) {

            param = new org.sbml.jsbml.Parameter(currentExpVar.getName());

            // Recurse into the expression variable definitions, so that one expression's
            // definition no longer just points to another expression
            while(currentExpVar.getExpression() instanceof ExpressionVariable)
                currentExpVar = (ExpressionVariable) currentExpVar.getExpression();

            if(!(currentExpVar.getExpression() instanceof dynetica.expression.Constant)) {
                // Use expression variable (in Dynetica) to create assignment rule (in SBML)
                math = expressionToASTNode((SimpleOperator) currentExpVar.getExpression());

                assignmentRule = new AssignmentRule(math, level, version);
                assignmentRule.setVariable(param);

                model.addRule(assignmentRule);
            }
        }

        // Add parameters from expression variable
        for (Parameter dynParam : (ArrayList<Parameter>) getParameters()) {
            if(!model.containsParameter(dynParam.toString()) && !dynParam.toString().equals("Time")) {
                param = new org.sbml.jsbml.Parameter(dynParam.toString());

                param.setValue(dynParam.getDefaultValue());

                model.addParameter(param);
            }
        }

        // Write to file

        SBMLWriter sbmlWriter = new SBMLWriter();

        try {
            sbmlWriter.write(doc, file.getAbsolutePath());
        } catch (SBMLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public ASTNode expressionToASTNode(SimpleOperator expression) {
        ASTNode astNode = new ASTNode();

        ArrayList<GeneralExpression> nodes = new ArrayList<GeneralExpression>();
        nodes.add(expression.getA());
        nodes.add(expression.getB());

        for(GeneralExpression node : nodes) {
            if (node instanceof SimpleOperator)
                astNode.addChild(expressionToASTNode((SimpleOperator) node));
            else {
                if (node instanceof Substance)
                    astNode.addChild(new ASTNode(((Substance) node).getName()));
                else if(node instanceof dynetica.expression.Constant) {
                    if((node.getValue() % 1) == 0)
                        astNode.addChild(new ASTNode((int) node.getValue()));
                    else
                        astNode.addChild(new ASTNode(node.getValue()));
                }
                else
                    astNode.addChild(new ASTNode(node.toString()));
            }
        }

        String expressionClass = expression.getClass().getSimpleName();

        if (expressionClass.equals("Multiply"))
            astNode.setType(ASTNode.Type.TIMES);
        else if (expressionClass.equals("Divide"))
            astNode.setType(ASTNode.Type.DIVIDE);
        else if (expressionClass.equals("Sum"))
            astNode.setType(ASTNode.Type.PLUS);
        else if (expressionClass.equals("Subtract"))
            astNode.setType(ASTNode.Type.MINUS);
        else if (expressionClass.equals("Pow"))
            astNode.setType(ASTNode.Type.POWER);
        else
            System.out.println(expression.getClass().getName());
        // astNode.setType(Character.toString(expression.getOperator()));

        return astNode;
    }

    // added 5/14/2005 LY
    // rebuild the system from its inputfile.
    public void rebuild() {
        substances.clear();
        progressiveReactions.clear();
        equilibratedReactions.clear();
        expressions.clear();
        entities.clear();
        SystemBuilder.rebuild(this, this.file);
    }

    public void rebuild(String inputText) {
        substances.clear();
        progressiveReactions.clear();
        equilibratedReactions.clear();
        expressions.clear();
        entities.clear();
        SystemBuilder.rebuild(this, inputText);

    }
}