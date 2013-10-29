package antlrp;// Generated from Gcode.4g by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GcodeVisitor<T> extends ParseTreeVisitor<T> {
	T visitFimdelinha(GcodeParser.FimdelinhaContext ctx);

	T visitG00(GcodeParser.G00Context ctx);

	T visitG01(GcodeParser.G01Context ctx);

	T visitCoordz(GcodeParser.CoordzContext ctx);

	T visitFimprograma(GcodeParser.FimprogramaContext ctx);

	T visitAvanco(GcodeParser.AvancoContext ctx);

	T visitDecimal3p2(GcodeParser.Decimal3p2Context ctx);

	T visitCoordx(GcodeParser.CoordxContext ctx);

	T visitDecimal2p2(GcodeParser.Decimal2p2Context ctx);

	T visitStatement(GcodeParser.StatementContext ctx);

	T visitCoord(GcodeParser.CoordContext ctx);

	T visitDecimal1p1(GcodeParser.Decimal1p1Context ctx);

	T visitG96(GcodeParser.G96Context ctx);

	T visitG76(GcodeParser.G76Context ctx);

	T visitDef(GcodeParser.DefContext ctx);

	T visitNumerolinha(GcodeParser.NumerolinhaContext ctx);
}