package antlrp;// Generated from Gcode.4g by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;

public class GcodeBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements GcodeVisitor<T> {
	@Override public T visitFimdelinha(GcodeParser.FimdelinhaContext ctx) { return visitChildren(ctx); }

	@Override public T visitG00(GcodeParser.G00Context ctx) { return visitChildren(ctx); }

	@Override public T visitG01(GcodeParser.G01Context ctx) { return visitChildren(ctx); }

	@Override public T visitCoordz(GcodeParser.CoordzContext ctx) { return visitChildren(ctx); }

	@Override public T visitFimprograma(GcodeParser.FimprogramaContext ctx) { return visitChildren(ctx); }

	@Override public T visitAvanco(GcodeParser.AvancoContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecimal3p2(GcodeParser.Decimal3p2Context ctx) { return visitChildren(ctx); }

	@Override public T visitCoordx(GcodeParser.CoordxContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecimal2p2(GcodeParser.Decimal2p2Context ctx) { return visitChildren(ctx); }

	@Override public T visitStatement(GcodeParser.StatementContext ctx) { return visitChildren(ctx); }

	@Override public T visitCoord(GcodeParser.CoordContext ctx) { return visitChildren(ctx); }

	@Override public T visitDecimal1p1(GcodeParser.Decimal1p1Context ctx) { return visitChildren(ctx); }

	@Override public T visitG96(GcodeParser.G96Context ctx) { return visitChildren(ctx); }

	@Override public T visitG76(GcodeParser.G76Context ctx) { return visitChildren(ctx); }

	@Override public T visitDef(GcodeParser.DefContext ctx) { return visitChildren(ctx); }

	@Override public T visitNumerolinha(GcodeParser.NumerolinhaContext ctx) { return visitChildren(ctx); }
}