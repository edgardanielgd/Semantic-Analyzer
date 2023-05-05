// Generated from D:/UNALArchivo/Lenguajes de Programación/SemanticAnalyzer/grammar\LPGrammar.g4 by ANTLR 4.12.0
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LPGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LPGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(LPGrammarParser.SContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#functiondeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctiondeclaration(LPGrammarParser.FunctiondeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#ifdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfdeclaration(LPGrammarParser.IfdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#ifcontinuation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfcontinuation(LPGrammarParser.IfcontinuationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#whiledeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhiledeclaration(LPGrammarParser.WhiledeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#fordeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFordeclaration(LPGrammarParser.FordeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#fordeclarationcomp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFordeclarationcomp(LPGrammarParser.FordeclarationcompContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#labelcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelcall(LPGrammarParser.LabelcallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LPGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#statementcomp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementcomp(LPGrammarParser.StatementcompContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#arrayaccessor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayaccessor(LPGrammarParser.ArrayaccessorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#mainstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainstatement(LPGrammarParser.MainstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#mainstatementscomp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainstatementscomp(LPGrammarParser.MainstatementscompContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(LPGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#actualexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualexpression(LPGrammarParser.ActualexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#expressionhelper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionhelper(LPGrammarParser.ExpressionhelperContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#notcomparatorexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotcomparatorexp(LPGrammarParser.NotcomparatorexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#notcomparatorexphelper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotcomparatorexphelper(LPGrammarParser.NotcomparatorexphelperContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#subexpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubexpression(LPGrammarParser.SubexpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(LPGrammarParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#andoroperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndoroperator(LPGrammarParser.AndoroperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#comparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparator(LPGrammarParser.ComparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#specialcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialcall(LPGrammarParser.SpecialcallContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#specialcallkeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialcallkeyword(LPGrammarParser.SpecialcallkeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(LPGrammarParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#optionalarg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionalarg(LPGrammarParser.OptionalargContext ctx);
	/**
	 * Visit a parse tree produced by {@link LPGrammarParser#furtherarguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFurtherarguments(LPGrammarParser.FurtherargumentsContext ctx);
}