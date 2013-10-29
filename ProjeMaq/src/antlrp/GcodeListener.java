package antlrp;// Generated from Gcode.4g by ANTLR 4.0
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface GcodeListener extends ParseTreeListener {
	void enterFimdelinha(GcodeParser.FimdelinhaContext ctx);
	void exitFimdelinha(GcodeParser.FimdelinhaContext ctx);

	void enterG00(GcodeParser.G00Context ctx);
	void exitG00(GcodeParser.G00Context ctx);

	void enterG01(GcodeParser.G01Context ctx);
	void exitG01(GcodeParser.G01Context ctx);

	void enterCoordz(GcodeParser.CoordzContext ctx);
	void exitCoordz(GcodeParser.CoordzContext ctx);

	void enterFimprograma(GcodeParser.FimprogramaContext ctx);
	void exitFimprograma(GcodeParser.FimprogramaContext ctx);

	void enterAvanco(GcodeParser.AvancoContext ctx);
	void exitAvanco(GcodeParser.AvancoContext ctx);

	void enterDecimal3p2(GcodeParser.Decimal3p2Context ctx);
	void exitDecimal3p2(GcodeParser.Decimal3p2Context ctx);

	void enterCoordx(GcodeParser.CoordxContext ctx);
	void exitCoordx(GcodeParser.CoordxContext ctx);

	void enterDecimal2p2(GcodeParser.Decimal2p2Context ctx);
	void exitDecimal2p2(GcodeParser.Decimal2p2Context ctx);

	void enterStatement(GcodeParser.StatementContext ctx);
	void exitStatement(GcodeParser.StatementContext ctx);

	void enterCoord(GcodeParser.CoordContext ctx);
	void exitCoord(GcodeParser.CoordContext ctx);

	void enterDecimal1p1(GcodeParser.Decimal1p1Context ctx);
	void exitDecimal1p1(GcodeParser.Decimal1p1Context ctx);

	void enterG96(GcodeParser.G96Context ctx);
	void exitG96(GcodeParser.G96Context ctx);

	void enterG76(GcodeParser.G76Context ctx);
	void exitG76(GcodeParser.G76Context ctx);

	void enterDef(GcodeParser.DefContext ctx);
	void exitDef(GcodeParser.DefContext ctx);

	void enterNumerolinha(GcodeParser.NumerolinhaContext ctx);
	void exitNumerolinha(GcodeParser.NumerolinhaContext ctx);
}