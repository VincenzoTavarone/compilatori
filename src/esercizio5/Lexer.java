/* The following code was generated by JFlex 1.4.3 on 19/09/17 10.32 */

package esercizio5;

import java_cup.runtime.*;

/**
 * This class is a scanner generated by <a href="http://www.jflex.de/">JFlex</a>
 * 1.4.3 on 19/09/17 10.32 from the specification file
 * <tt>/home/vincenzo/Scrivania/compilatori/src/esercizio5/spec.lex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

	/** This character denotes the end of file */
	public static final int YYEOF = -1;

	/** initial size of the lookahead buffer */
	private static final int ZZ_BUFFERSIZE = 16384;

	/** lexical states */
	public static final int STRING = 2;
	public static final int YYINITIAL = 0;

	/**
	 * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
	 * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l at the
	 * beginning of a line l is of the form l = 2*k, k a non negative integer
	 */
	private static final int ZZ_LEXSTATE[] = { 0, 0, 1, 1 };

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED = "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\17\1\47"
			+ "\3\0\1\54\1\7\1\13\1\14\1\52\1\50\1\12\1\21\1\11"
			+ "\1\53\1\6\11\4\1\15\1\10\1\20\1\16\1\22\2\0\32\5"
			+ "\6\0\1\34\1\31\1\44\1\41\1\26\1\35\1\27\1\36\1\23"
			+ "\2\5\1\33\1\46\1\24\1\32\1\43\1\5\1\30\1\37\1\25"
			+ "\1\45\1\42\1\40\3\5\1\0\1\51\uff83\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 = "\2\0\2\1\1\2\1\3\1\2\1\0\1\4\1\5"
			+ "\1\6\1\7\1\10\2\0\1\11\1\12\1\13\1\14"
			+ "\11\3\1\15\1\16\1\0\1\17\1\20\1\0\1\21"
			+ "\1\22\1\0\1\23\1\24\1\25\1\26\1\27\1\30"
			+ "\1\31\1\3\1\32\10\3\1\33\2\3\1\34\1\35"
			+ "\1\36\3\3\1\37\5\3\1\40\2\3\1\41\1\42"
			+ "\1\43\7\3\1\44\1\3\1\45\1\46\6\3\1\47" + "\1\50\1\51\2\3\1\52";

	private static int[] zzUnpackAction() {
		int[] result = new int[98];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/**
	 * Translates a state to a row index in the transition table
	 */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

	private static final String ZZ_ROWMAP_PACKED_0 = "\0\0\0\55\0\132\0\207\0\264\0\341\0\207\0\u010e"
			+ "\0\207\0\207\0\207\0\207\0\207\0\u013b\0\u0168\0\u0195"
			+ "\0\u01c2\0\u01ef\0\u021c\0\u0249\0\u0276\0\u02a3\0\u02d0\0\u02fd"
			+ "\0\u032a\0\u0357\0\u0384\0\u03b1\0\207\0\207\0\u03de\0\207"
			+ "\0\207\0\u040b\0\207\0\207\0\u0438\0\207\0\207\0\207"
			+ "\0\207\0\207\0\207\0\207\0\u0465\0\341\0\u0492\0\u04bf"
			+ "\0\u04ec\0\u0519\0\u0546\0\u0573\0\u05a0\0\u05cd\0\341\0\u05fa"
			+ "\0\u0627\0\207\0\207\0\207\0\u0654\0\u0681\0\u06ae\0\341"
			+ "\0\u06db\0\u0708\0\u0735\0\u0762\0\u078f\0\341\0\u07bc\0\u07e9"
			+ "\0\341\0\341\0\341\0\u0816\0\u0843\0\u0870\0\u089d\0\u08ca"
			+ "\0\u08f7\0\u0924\0\341\0\u0951\0\341\0\341\0\u097e\0\u09ab"
			+ "\0\u09d8\0\u0a05\0\u0a32\0\u0a5f\0\341\0\341\0\341\0\u0a8c"
			+ "\0\u0ab9\0\341";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[98];
		int offset = 0;
		offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackRowMap(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int high = packed.charAt(i++) << 16;
			result[j++] = high | packed.charAt(i++);
		}
		return j;
	}

	/**
	 * The transition table of the DFA
	 */
	private static final int[] ZZ_TRANS = zzUnpackTrans();

	private static final String ZZ_TRANS_PACKED_0 = "\1\0\1\3\2\4\1\5\1\6\1\7\1\10\1\11"
			+ "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"
			+ "\1\22\1\23\1\24\1\6\1\25\1\26\2\6\1\27"
			+ "\3\6\1\30\2\6\1\31\1\32\1\33\1\34\3\6"
			+ "\1\35\1\36\1\37\1\40\1\41\1\42\2\43\1\0"
			+ "\44\43\1\44\5\43\2\0\1\4\133\0\1\5\1\0"
			+ "\1\5\52\0\3\6\14\0\24\6\6\0\7\45\1\0"
			+ "\45\45\16\0\1\46\54\0\1\47\54\0\1\50\54\0"
			+ "\1\51\2\0\1\52\55\0\1\53\50\0\1\54\42\0"
			+ "\3\6\14\0\1\6\1\55\10\6\1\56\11\6\12\0"
			+ "\3\6\14\0\5\6\1\57\5\6\1\60\10\6\12\0"
			+ "\3\6\14\0\1\6\1\61\6\6\1\62\13\6\12\0"
			+ "\3\6\14\0\3\6\1\63\3\6\1\64\14\6\12\0"
			+ "\3\6\14\0\11\6\1\65\12\6\12\0\3\6\14\0"
			+ "\13\6\1\66\10\6\12\0\3\6\14\0\7\6\1\67"
			+ "\14\6\12\0\3\6\14\0\11\6\1\70\12\6\12\0"
			+ "\3\6\14\0\5\6\1\71\16\6\57\0\1\72\57\0"
			+ "\1\73\7\0\1\74\51\0\3\6\14\0\2\6\1\75"
			+ "\21\6\12\0\3\6\14\0\22\6\1\76\1\6\12\0"
			+ "\3\6\14\0\3\6\1\77\20\6\12\0\3\6\14\0"
			+ "\16\6\1\100\5\6\12\0\3\6\14\0\14\6\1\101"
			+ "\7\6\12\0\3\6\14\0\4\6\1\102\17\6\12\0"
			+ "\3\6\14\0\7\6\1\103\14\6\12\0\3\6\14\0"
			+ "\10\6\1\104\13\6\12\0\3\6\14\0\1\105\23\6"
			+ "\12\0\3\6\14\0\5\6\1\106\16\6\12\0\3\6"
			+ "\14\0\7\6\1\107\14\6\12\0\3\6\14\0\3\6"
			+ "\1\110\20\6\12\0\3\6\14\0\3\6\1\111\20\6"
			+ "\12\0\3\6\14\0\1\6\1\112\22\6\12\0\3\6"
			+ "\14\0\3\6\1\113\20\6\12\0\3\6\14\0\1\114"
			+ "\23\6\12\0\3\6\14\0\10\6\1\115\13\6\12\0"
			+ "\3\6\14\0\14\6\1\116\7\6\12\0\3\6\14\0"
			+ "\10\6\1\117\13\6\12\0\3\6\14\0\4\6\1\120"
			+ "\14\6\1\121\2\6\12\0\3\6\14\0\4\6\1\122"
			+ "\17\6\12\0\3\6\14\0\1\6\1\123\22\6\12\0"
			+ "\3\6\14\0\3\6\1\124\20\6\12\0\3\6\14\0"
			+ "\3\6\1\125\20\6\12\0\3\6\14\0\3\6\1\126"
			+ "\20\6\12\0\3\6\14\0\5\6\1\127\16\6\12\0"
			+ "\3\6\14\0\3\6\1\130\20\6\12\0\3\6\14\0"
			+ "\3\6\1\131\20\6\12\0\3\6\14\0\11\6\1\132"
			+ "\12\6\12\0\3\6\14\0\11\6\1\133\12\6\12\0"
			+ "\3\6\14\0\16\6\1\134\5\6\12\0\3\6\14\0"
			+ "\5\6\1\135\16\6\12\0\3\6\14\0\1\6\1\136"
			+ "\22\6\12\0\3\6\14\0\23\6\1\137\12\0\3\6"
			+ "\14\0\22\6\1\140\1\6\12\0\3\6\14\0\5\6"
			+ "\1\141\16\6\12\0\3\6\14\0\3\6\1\142\20\6" + "\6\0";

	private static int[] zzUnpackTrans() {
		int[] result = new int[2790];
		int offset = 0;
		offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackTrans(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			value--;
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;

	/* error messages for the codes above */
	private static final String ZZ_ERROR_MSG[] = {
			"Unkown internal scanner error", "Error: could not match input",
			"Error: pushback value was too large" };

	/**
	 * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
	 */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	private static final String ZZ_ATTRIBUTE_PACKED_0 = "\2\0\1\1\1\11\2\1\1\11\1\0\5\11\2\0"
			+ "\15\1\2\11\1\0\2\11\1\0\2\11\1\0\7\11" + "\15\1\3\11\46\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[98];
		int offset = 0;
		offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAttribute(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/** the input device */
	private java.io.Reader zzReader;

	/** the current state of the DFA */
	private int zzState;

	/** the current lexical state */
	private int zzLexicalState = YYINITIAL;

	/**
	 * this buffer contains the current text to be matched and is the source of
	 * the yytext() string
	 */
	private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

	/** the textposition at the last accepting state */
	private int zzMarkedPos;

	/** the current text position in the buffer */
	private int zzCurrentPos;

	/** startRead marks the beginning of the yytext() string in the buffer */
	private int zzStartRead;

	/**
	 * endRead marks the last character in the buffer, that has been read from
	 * input
	 */
	private int zzEndRead;

	/** number of newlines encountered up to the start of the matched text */
	private int yyline;

	/** the number of characters up to the start of the matched text */
	private int yychar;

	/**
	 * the number of characters from the last newline up to the start of the
	 * matched text
	 */
	private int yycolumn;

	/**
	 * zzAtBOL == true <=> the scanner is currently at the beginning of a line
	 */
	private boolean zzAtBOL = true;

	/** zzAtEOF == true <=> the scanner is at the EOF */
	private boolean zzAtEOF;

	/** denotes if the user-EOF-code has already been executed */
	private boolean zzEOFDone;

	/* user code: */
	StringBuffer string = new StringBuffer();
	TableOfSymbols table = new TableOfSymbols();

	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}

	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}

	public TableOfSymbols getTable() {
		return table;
	}

	/**
	 * Creates a new scanner There is also a java.io.InputStream version of this
	 * constructor.
	 * 
	 * @param in
	 *            the java.io.Reader to read input from.
	 */
	Lexer(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner. There is also java.io.Reader version of this
	 * constructor.
	 * 
	 * @param in
	 *            the java.io.Inputstream to read input from.
	 */
	Lexer(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
	}

	/**
	 * Unpacks the compressed character translation table.
	 * 
	 * @param packed
	 *            the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0; /* index in packed string */
		int j = 0; /* index in unpacked array */
		while (i < 114) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do
				map[j++] = value;
			while (--count > 0);
		}
		return map;
	}

	/**
	 * Refills the input buffer.
	 * 
	 * @return <code>false</code>, iff there was new input.
	 * 
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	private boolean zzRefill() throws java.io.IOException {

		/* first: make room (if you can) */
		if (zzStartRead > 0) {
			System.arraycopy(zzBuffer, zzStartRead, zzBuffer, 0, zzEndRead
					- zzStartRead);

			/* translate stored positions */
			zzEndRead -= zzStartRead;
			zzCurrentPos -= zzStartRead;
			zzMarkedPos -= zzStartRead;
			zzStartRead = 0;
		}

		/* is the buffer big enough? */
		if (zzCurrentPos >= zzBuffer.length) {
			/* if not: blow it up */
			char newBuffer[] = new char[zzCurrentPos * 2];
			System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
			zzBuffer = newBuffer;
		}

		/* finally: fill the buffer with new input */
		int numRead = zzReader.read(zzBuffer, zzEndRead, zzBuffer.length
				- zzEndRead);

		if (numRead > 0) {
			zzEndRead += numRead;
			return false;
		}
		// unlikely but not impossible: read 0 characters, but not at end of
		// stream
		if (numRead == 0) {
			int c = zzReader.read();
			if (c == -1) {
				return true;
			} else {
				zzBuffer[zzEndRead++] = (char) c;
				return false;
			}
		}

		// numRead < 0
		return true;
	}

	/**
	 * Closes the input stream.
	 */
	public final void yyclose() throws java.io.IOException {
		zzAtEOF = true; /* indicate end of file */
		zzEndRead = zzStartRead; /* invalidate buffer */

		if (zzReader != null)
			zzReader.close();
	}

	/**
	 * Resets the scanner to read from a new input stream. Does not close the
	 * old reader.
	 * 
	 * All internal variables are reset, the old input stream <b>cannot</b> be
	 * reused (internal buffer is discarded and lost). Lexical state is set to
	 * <tt>ZZ_INITIAL</tt>.
	 * 
	 * @param reader
	 *            the new input stream
	 */
	public final void yyreset(java.io.Reader reader) {
		zzReader = reader;
		zzAtBOL = true;
		zzAtEOF = false;
		zzEOFDone = false;
		zzEndRead = zzStartRead = 0;
		zzCurrentPos = zzMarkedPos = 0;
		yyline = yychar = yycolumn = 0;
		zzLexicalState = YYINITIAL;
	}

	/**
	 * Returns the current lexical state.
	 */
	public final int yystate() {
		return zzLexicalState;
	}

	/**
	 * Enters a new lexical state
	 * 
	 * @param newState
	 *            the new lexical state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}

	/**
	 * Returns the text matched by the current regular expression.
	 */
	public final String yytext() {
		return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
	}

	/**
	 * Returns the character at position <tt>pos</tt> from the matched text.
	 * 
	 * It is equivalent to yytext().charAt(pos), but faster
	 * 
	 * @param pos
	 *            the position of the character to fetch. A value from 0 to
	 *            yylength()-1.
	 * 
	 * @return the character at position pos
	 */
	public final char yycharat(int pos) {
		return zzBuffer[zzStartRead + pos];
	}

	/**
	 * Returns the length of the matched text region.
	 */
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}

	/**
	 * Reports an error that occured while scanning.
	 * 
	 * In a wellformed scanner (no or only correct usage of yypushback(int) and
	 * a match-all fallback rule) this method will only be called with things
	 * that "Can't Possibly Happen". If this method is called, something is
	 * seriously wrong (e.g. a JFlex bug producing a faulty scanner etc.).
	 * 
	 * Usual syntax/scanner level error handling should be done in error
	 * fallback rules.
	 * 
	 * @param errorCode
	 *            the code of the errormessage to display
	 */
	private void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}

		throw new Error(message);
	}

	/**
	 * Pushes the specified amount of characters back into the input stream.
	 * 
	 * They will be read again by then next call of the scanning method
	 * 
	 * @param number
	 *            the number of characters to be read again. This number must
	 *            not be greater than yylength()!
	 */
	public void yypushback(int number) {
		if (number > yylength())
			zzScanError(ZZ_PUSHBACK_2BIG);

		zzMarkedPos -= number;
	}

	/**
	 * Contains user EOF-code, which will be executed exactly once, when the end
	 * of file is reached
	 */
	private void zzDoEOF() throws java.io.IOException {
		if (!zzEOFDone) {
			zzEOFDone = true;
			yyclose();
		}
	}

	/**
	 * Resumes scanning until the next regular expression is matched, the end of
	 * input is encountered or an I/O-Error occurs.
	 * 
	 * @return the next token
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	public java_cup.runtime.Symbol next_token() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		char[] zzBufferL = zzBuffer;
		char[] zzCMapL = ZZ_CMAP;

		int[] zzTransL = ZZ_TRANS;
		int[] zzRowMapL = ZZ_ROWMAP;
		int[] zzAttrL = ZZ_ATTRIBUTE;

		while (true) {
			zzMarkedPosL = zzMarkedPos;

			boolean zzR = false;
			for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL; zzCurrentPosL++) {
				switch (zzBufferL[zzCurrentPosL]) {
				case '\u000B':
				case '\u000C':
				case '\u0085':
				case '\u2028':
				case '\u2029':
					yyline++;
					yycolumn = 0;
					zzR = false;
					break;
				case '\r':
					yyline++;
					yycolumn = 0;
					zzR = true;
					break;
				case '\n':
					if (zzR)
						zzR = false;
					else {
						yyline++;
						yycolumn = 0;
					}
					break;
				default:
					zzR = false;
					yycolumn++;
				}
			}

			if (zzR) {
				// peek one character ahead if it is \n (if we have counted one
				// line too much)
				boolean zzPeek;
				if (zzMarkedPosL < zzEndReadL)
					zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				else if (zzAtEOF)
					zzPeek = false;
				else {
					boolean eof = zzRefill();
					zzEndReadL = zzEndRead;
					zzMarkedPosL = zzMarkedPos;
					zzBufferL = zzBuffer;
					if (eof)
						zzPeek = false;
					else
						zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				}
				if (zzPeek)
					yyline--;
			}
			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = ZZ_LEXSTATE[zzLexicalState];

			zzForAction: {
				while (true) {

					if (zzCurrentPosL < zzEndReadL)
						zzInput = zzBufferL[zzCurrentPosL++];
					else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						} else {
							zzInput = zzBufferL[zzCurrentPosL++];
						}
					}
					int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
					if (zzNext == -1)
						break zzForAction;
					zzState = zzNext;

					int zzAttributes = zzAttrL[zzState];
					if ((zzAttributes & 1) == 1) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if ((zzAttributes & 8) == 8)
							break zzForAction;
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
			case 24: {
				return symbol(sym.WRITE);
			}
			case 43:
				break;
			case 5: {
				return symbol(sym.END_PROGRAM);
			}
			case 44:
				break;
			case 2: {
				table.add(symbol(sym.INTEGER_CONSTANT, yytext()));
				return symbol(sym.INTEGER_CONSTANT, yytext());
			}
			case 45:
				break;
			case 7: {
				return symbol(sym.LPAR);
			}
			case 46:
				break;
			case 14: {
				return symbol(sym.ADDING_OPERATOR, "+");
			}
			case 47:
				break;
			case 18: {
				table.add(symbol(sym.STRING_CONSTANT, yytext()));
				yybegin(YYINITIAL);
				return symbol(sym.STRING_CONSTANT, string.toString());
			}
			case 48:
				break;
			case 31: {
				return symbol(sym.END);
			}
			case 49:
				break;
			case 13: {
				string.setLength(0);
				yybegin(STRING);
			}
			case 50:
				break;
			case 4: {
				return symbol(sym.INSTRUCTION_SEPARATOR);
			}
			case 51:
				break;
			case 8: {
				return symbol(sym.RPAR);
			}
			case 52:
				break;
			case 36: {
				return symbol(sym.BEGIN);
			}
			case 53:
				break;
			case 9: {
				return symbol(sym.NOT);
			}
			case 54:
				break;
			case 19: {
				return symbol(sym.ASSIGN);
			}
			case 55:
				break;
			case 3: {
				table.add(symbol(sym.IDENTIFIER, yytext()));
				return symbol(sym.IDENTIFIER, yytext());
			}
			case 56:
				break;
			case 21: {
				return symbol(sym.RELATIONAL_OPERATOR, "!=");
			}
			case 57:
				break;
			case 33: {
				return symbol(sym.TRUE);
			}
			case 58:
				break;
			case 1: { /* ignore */
			}
			case 59:
				break;
			case 25: {
				return symbol(sym.RELATIONAL_OPERATOR, ">=");
			}
			case 60:
				break;
			case 12: {
				return symbol(sym.RELATIONAL_OPERATOR, ">");
			}
			case 61:
				break;
			case 27: {
				return symbol(sym.DO);
			}
			case 62:
				break;
			case 6: {
				return symbol(sym.SEPARATOR);
			}
			case 63:
				break;
			case 35: {
				return symbol(sym.ELSE);
			}
			case 64:
				break;
			case 34: {
				return symbol(sym.THEN);
			}
			case 65:
				break;
			case 32: {
				return symbol(sym.VAR);
			}
			case 66:
				break;
			case 38: {
				return symbol(sym.WHILE);
			}
			case 67:
				break;
			case 42: {
				return symbol(sym.PROCEDURE);
			}
			case 68:
				break;
			case 26: {
				return symbol(sym.IF);
			}
			case 69:
				break;
			case 28: {
				return symbol(sym.ADDING_OPERATOR, "||");
			}
			case 70:
				break;
			case 10: {
				return symbol(sym.RELATIONAL_OPERATOR, "<");
			}
			case 71:
				break;
			case 15: {
				return symbol(sym.MULTIPLYING_OPERATOR, "*");
			}
			case 72:
				break;
			case 30: {
				table.add(symbol(sym.CHARACTER_CONSTANT, yytext()));
				return symbol(sym.CHARACTER_CONSTANT, yytext());
			}
			case 73:
				break;
			case 16: {
				return symbol(sym.MULTIPLYING_OPERATOR, "/");
			}
			case 74:
				break;
			case 20: {
				return symbol(sym.RELATIONAL_OPERATOR, "==");
			}
			case 75:
				break;
			case 39: {
				return symbol(sym.INTEGER);
			}
			case 76:
				break;
			case 11: {
				return symbol(sym.MINUS);
			}
			case 77:
				break;
			case 40: {
				return symbol(sym.BOOLEAN);
			}
			case 78:
				break;
			case 23: {
				return symbol(sym.READ);
			}
			case 79:
				break;
			case 29: {
				return symbol(sym.MULTIPLYING_OPERATOR, "&&");
			}
			case 80:
				break;
			case 37: {
				return symbol(sym.FALSE);
			}
			case 81:
				break;
			case 17: {
				string.append(yytext());
			}
			case 82:
				break;
			case 22: {
				return symbol(sym.RELATIONAL_OPERATOR, "<=");
			}
			case 83:
				break;
			case 41: {
				return symbol(sym.PROGRAM);
			}
			case 84:
				break;
			default:
				if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
					zzAtEOF = true;
					zzDoEOF();
					{
						return new java_cup.runtime.Symbol(sym.EOF);
					}
				} else {
					zzScanError(ZZ_NO_MATCH);
				}
			}
		}
	}

}
