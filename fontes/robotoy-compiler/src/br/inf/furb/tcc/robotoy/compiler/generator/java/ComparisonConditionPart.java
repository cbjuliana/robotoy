package br.inf.furb.tcc.robotoy.compiler.generator.java;

import br.inf.furb.tcc.robotoy.compiler.analyzer.SemanticError;
import br.inf.furb.tcc.robotoy.compiler.analyzer.message.SemanticValidationMessage;

public class ComparisonConditionPart {

    private Member firstMember = new Member();
    private Member secondMember = new Member();
    private Member memberInEdition = firstMember;
    private ComparisonType comparisonType;

    public void setFirstMemberType(Type type) {
        firstMember.setType(type);
    }

    public void setSecondMemberType(Type type) {
        secondMember.setType(type);
    }

    public Type getType() {
        return memberInEdition.getType();
    }

    public void setType(Type type, int line) throws SemanticError {
        if (memberInEdition.getType() == null) {
            memberInEdition.setType(type);
        } else {
            assertSameType(type, line);
        }
    }

    private void assertSameType(Type type, int line) throws SemanticError {
        if (type != memberInEdition.getType()) {
            String element;
            if (memberInEdition == firstMember) {
                element = "primeiro";
            } else {
                element = "segundo";
            }
            throw new SemanticError(line, String.format(SemanticValidationMessage.INVALID_ELEMENTS_RELATION, element, memberInEdition.getType().getRobotoyType(), type.getRobotoyType()));
        }
    }

    public void appendContent(String content) {
        memberInEdition.appendContent(content);
    }

    public void setComparisonType(ComparisonType comparisonType) {
        this.comparisonType = comparisonType;
        memberInEdition = secondMember;
    }

    public String getCondition(int line) throws SemanticError {
        assertCompatibleTypesForComparision(line);
        assertCompatibleComparisonOperator(line);

        StringBuilder condition = new StringBuilder();
        if (firstMember.getType() == Type.TEXT) {
            if (comparisonType == ComparisonType.DIFFERENT) {
                condition.append("!");
            }
            if (firstMember.getContent().matches("^([A-z]+[A-z0-9]*|\".*\")(\\s\\+\\s([A-z]+[A-z0-9]*|\".*\"))+$")) {
                condition.append("(");
                condition.append(firstMember.getContent());
                condition.append(")");
            } else {
                condition.append(firstMember.getContent());
            }
            condition.append(".equals(");
            condition.append(secondMember.getContent());
            condition.append(")");
        } else {
            condition.append(firstMember.getContent());
            condition.append(" ");
            condition.append(comparisonType.getJavaOperator());
            condition.append(" ");
            condition.append(secondMember.getContent());
        }

        return condition.toString();
    }

    private void assertCompatibleComparisonOperator(int line) throws SemanticError {
        Type type = firstMember.getType();
        if (type == Type.TEXT || type == Type.COLOR) {
            if (comparisonType != ComparisonType.EQUAL & comparisonType != ComparisonType.DIFFERENT) {
                throw new SemanticError(line, String.format(SemanticValidationMessage.INCOMPATIBLE_OPERATOR_FOR_COMPARISON, type.getRobotoyType()));
            }
        }
    }

    private void assertCompatibleTypesForComparision(int line) throws SemanticError {
        Type firstMemberType = firstMember.getType();
        Type secondMemberType = secondMember.getType();
        if (firstMemberType != secondMemberType) {
            if (!(firstMemberType.getRobotoyType().equals("texto") || secondMemberType.getRobotoyType().equals("texto")) &&
                    (firstMemberType.getRobotoyType().equals("cor") || secondMemberType.getRobotoyType().equals("cor")))
                                 
            throw new SemanticError(line, String.format(SemanticValidationMessage.INVALID_COMPARISION, firstMemberType.getRobotoyType(), secondMemberType.getRobotoyType()));
        }
    }

    private class Member {

        private Type type;
        private StringBuilder content = new StringBuilder();

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public void appendContent(String content) {
            this.content.append(content);
        }

        public String getContent() {
            return content.toString();
        }

    }

}
