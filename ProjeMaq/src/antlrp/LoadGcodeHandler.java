package antlrp;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

public class LoadGcodeHandler {
	private List<LinhaPrograma> programa = new ArrayList<LinhaPrograma>();
	private List<String> errorList = null;

	public void loadProgram(String filename) throws Exception {
		ANTLRFileStream f = new ANTLRFileStream(filename);
		GcodeLexer lex = new GcodeLexer(f); //new GcodeLexer (new ANTLRInputStream("N000 G00 X1 Z2\r\nN011 G01 X-1. Z2.2 F\r\nN002 M30\r"));//
		CommonTokenStream tokens = new CommonTokenStream(lex);
		GcodeParser parser = new GcodeParser(tokens);
		ErrorHandler errorHandler = new ErrorHandler();
		parser.addErrorListener(errorHandler);
		errorList = new ArrayList<String>();
		ParseTree tree = parser.def();
		if ((tree != null) && (errorList.isEmpty())) {
			GcodeInterpreter p = new GcodeInterpreter(programa);
			p.visit(tree);
			System.out.println("...");
		} else {
			throw new Exception(errorList.get(0));
		}
	}

	public List<String> getProgramList() {
		List<String> listagem = new ArrayList<String>();
		String linha;
		for (LinhaPrograma p : programa) {
			if (p.codigoG != null) {
				linha = "N" + p.numeroLinha + " " + p.codigoG + " X" + p.x + " Z" + p.z;
			} else {
				linha = "N" + p.numeroLinha + " " + p.codigoM;
			}
			listagem.add(linha);
		}
		return listagem;
	}

	class ErrorHandler extends BaseErrorListener {

		@Override
		public void syntaxError(Recognizer<?, ?> recognizer,
				Object offendingSymbol, int line, int charPositionInLine,
				String msg, RecognitionException e) {
					int msgSize = msg.split(" ").length;
				errorList.add("ERRO: Linha:" + line + ", Posição:" + charPositionInLine + ", Palavra:" + msg.split(" ")[msgSize-1]);
		}
	}
}