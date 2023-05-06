// Generated from D:/UNALArchivo/Lenguajes de Programación/SemanticAnalyzer/grammar\LPGrammar.g4 by ANTLR 4.12.0
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LPGrammarParser}.
 */
public interface LPGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(LPGrammarParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(LPGrammarParser.SContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#functiondeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctiondeclaration(LPGrammarParser.FunctiondeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#functiondeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctiondeclaration(LPGrammarParser.FunctiondeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#ifdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterIfdeclaration(LPGrammarParser.IfdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#ifdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitIfdeclaration(LPGrammarParser.IfdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#ifcontinuation}.
	 * @param ctx the parse tree
	 */
	void enterIfcontinuation(LPGrammarParser.IfcontinuationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#ifcontinuation}.
	 * @param ctx the parse tree
	 */
	void exitIfcontinuation(LPGrammarParser.IfcontinuationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#whiledeclaration}.
	 * @param ctx the parse tree
	 */
	void enterWhiledeclaration(LPGrammarParser.WhiledeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#whiledeclaration}.
	 * @param ctx the parse tree
	 */
	void exitWhiledeclaration(LPGrammarParser.WhiledeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#fordeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFordeclaration(LPGrammarParser.FordeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#fordeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFordeclaration(LPGrammarParser.FordeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#fordeclarationcomp}.
	 * @param ctx the parse tree
	 */
	void enterFordeclarationcomp(LPGrammarParser.FordeclarationcompContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#fordeclarationcomp}.
	 * @param ctx the parse tree
	 */
	void exitFordeclarationcomp(LPGrammarParser.FordeclarationcompContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#labelcall}.
	 * @param ctx the parse tree
	 */
	void enterLabelcall(LPGrammarParser.LabelcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#labelcall}.
	 * @param ctx the parse tree
	 */
	void exitLabelcall(LPGrammarParser.LabelcallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LPGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LPGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#statementcomp}.
	 * @param ctx the parse tree
	 */
	void enterStatementcomp(LPGrammarParser.StatementcompContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#statementcomp}.
	 * @param ctx the parse tree
	 */
	void exitStatementcomp(LPGrammarParser.StatementcompContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#arrayaccessor}.
	 * @param ctx the parse tree
	 */
	void enterArrayaccessor(LPGrammarParser.ArrayaccessorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#arrayaccessor}.
	 * @param ctx the parse tree
	 */
	void exitArrayaccessor(LPGrammarParser.ArrayaccessorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#mainstatement}.
	 * @param ctx the parse tree
	 */
	void enterMainstatement(LPGrammarParser.MainstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#mainstatement}.
	 * @param ctx the parse tree
	 */
	void exitMainstatement(LPGrammarParser.MainstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#mainstatementscomp}.
	 * @param ctx the parse tree
	 */
	void enterMainstatementscomp(LPGrammarParser.MainstatementscompContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#mainstatementscomp}.
	 * @param ctx the parse tree
	 */
	void exitMainstatementscomp(LPGrammarParser.MainstatementscompContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LPGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LPGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#actualexpression}.
	 * @param ctx the parse tree
	 */
	void enterActualexpression(LPGrammarParser.ActualexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#actualexpression}.
	 * @param ctx the parse tree
	 */
	void exitActualexpression(LPGrammarParser.ActualexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#expressionhelper}.
	 * @param ctx the parse tree
	 */
	void enterExpressionhelper(LPGrammarParser.ExpressionhelperContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#expressionhelper}.
	 * @param ctx the parse tree
	 */
	void exitExpressionhelper(LPGrammarParser.ExpressionhelperContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#notcomparatorexp}.
	 * @param ctx the parse tree
	 */
	void enterNotcomparatorexp(LPGrammarParser.NotcomparatorexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#notcomparatorexp}.
	 * @param ctx the parse tree
	 */
	void exitNotcomparatorexp(LPGrammarParser.NotcomparatorexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#notcomparatorexphelper}.
	 * @param ctx the parse tree
	 */
	void enterNotcomparatorexphelper(LPGrammarParser.NotcomparatorexphelperContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#notcomparatorexphelper}.
	 * @param ctx the parse tree
	 */
	void exitNotcomparatorexphelper(LPGrammarParser.NotcomparatorexphelperContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#subexpression}.
	 * @param ctx the parse tree
	 */
	void enterSubexpression(LPGrammarParser.SubexpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#subexpression}.
	 * @param ctx the parse tree
	 */
	void exitSubexpression(LPGrammarParser.SubexpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(LPGrammarParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(LPGrammarParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#andoroperator}.
	 * @param ctx the parse tree
	 */
	void enterAndoroperator(LPGrammarParser.AndoroperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#andoroperator}.
	 * @param ctx the parse tree
	 */
	void exitAndoroperator(LPGrammarParser.AndoroperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(LPGrammarParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(LPGrammarParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#specialcall}.
	 * @param ctx the parse tree
	 */
	void enterSpecialcall(LPGrammarParser.SpecialcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#specialcall}.
	 * @param ctx the parse tree
	 */
	void exitSpecialcall(LPGrammarParser.SpecialcallContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#specialcallkeyword}.
	 * @param ctx the parse tree
	 */
	void enterSpecialcallkeyword(LPGrammarParser.SpecialcallkeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#specialcallkeyword}.
	 * @param ctx the parse tree
	 */
	void exitSpecialcallkeyword(LPGrammarParser.SpecialcallkeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(LPGrammarParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(LPGrammarParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#optionalarg}.
	 * @param ctx the parse tree
	 */
	void enterOptionalarg(LPGrammarParser.OptionalargContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#optionalarg}.
	 * @param ctx the parse tree
	 */
	void exitOptionalarg(LPGrammarParser.OptionalargContext ctx);
	/**
	 * Enter a parse tree produced by {@link LPGrammarParser#furtherarguments}.
	 * @param ctx the parse tree
	 */
	void enterFurtherarguments(LPGrammarParser.FurtherargumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LPGrammarParser#furtherarguments}.
	 * @param ctx the parse tree
	 */
	void exitFurtherarguments(LPGrammarParser.FurtherargumentsContext ctx);
}