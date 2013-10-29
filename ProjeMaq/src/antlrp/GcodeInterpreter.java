package antlrp;

import java.util.List;

import antlrp.GcodeParser.*;

public class GcodeInterpreter extends GcodeBaseVisitor {
	List<LinhaPrograma> programaNC = null;

	@Override
	public Object visitStatement(StatementContext ctx) {
		LinhaPrograma linha = new LinhaPrograma(); // linha com funcao G

		linha.numeroLinha = ctx.numerolinha().getText();
		programaNC.add(linha);
		return super.visitStatement(ctx);
	}

	@Override
	public Object visitFimprograma(FimprogramaContext ctx) {
		LinhaPrograma linha = new LinhaPrograma();
		linha.codigoM = ctx.MFIM().getText();
		linha.codigoG = null;
		linha.numeroLinha = ctx.numerolinha().getText();
		programaNC.add(linha);
		return super.visitFimprograma(ctx);
	}

	public GcodeInterpreter(List<LinhaPrograma> programa) {
		this.programaNC = programa;
	} // constructor
}
