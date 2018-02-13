/**
 * SimpleOperator.java
 *
 *
 * Created: Sat Aug 26 23:56:47 2000
 *
 * @author  Lingchong You
 * @version 0.1
 */

package dynetica.expression;

/**
 * SimpleOperator implements the foundation most fundamental mathematical
 * operations:(, ),+, -, *, /, and ^.
 */

public class SimpleOperator extends Expression {
    char operator;

    public SimpleOperator(char c, GeneralExpression a, GeneralExpression b) {
        super(a, b);
        operator = c;
        switch (c) {
        case '+':
            type = ExpressionConstants.SUM;
            break;
        case '-':
            type = ExpressionConstants.SUBTRACT;
            break;
        case '*':
            type = ExpressionConstants.MULTIPLY;
            break;
        case '/':
            type = ExpressionConstants.DIVIDE;
            break;
        case '^':
            type = ExpressionConstants.POW;
            break;

//added by LY. 6/2016 to implement assignment (a = b).
        case '=':
            type = ExpressionConstants.ASSIGNMENT;
            break;
        default:
            System.out.println("Unknown operator:" + c);
        }
    }

    public static boolean isSimpleOperator(char c) {
        return (c == '(' || c == ')' || c == '+' || c == '-' || c == '*'
                || c == '/' || c == '^' || c == ',');
    }

    public static boolean isBracket(char c) {
        return (c == '[' || c == ']');
    }

    public void compute() {
        switch (type) {
        case ExpressionConstants.SUM:
            value = a.getValue() + b.getValue();
        case ExpressionConstants.SUBTRACT:
            value = a.getValue() - b.getValue();
        case ExpressionConstants.MULTIPLY:
            value = a.getValue() * b.getValue();
        case ExpressionConstants.DIVIDE:
            value = a.getValue() / b.getValue();
        case ExpressionConstants.POW:
            value = Math.pow(a.getValue(), b.getValue());
        default:
            value = 0.0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (a.getType() < getType())
            sb.append("(").append(a).append(")");
        else
            sb.append(a);

        sb.append(" ").append(operator).append(" ");

        if (b.getType() <= getType())
            sb.append("(").append(b).append(")");
        else
            sb.append(b);
        return sb.toString();
    }

} // SimpleOperator
