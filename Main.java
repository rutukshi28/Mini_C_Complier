// import java.util.*;
// import java.io.*;
// import java.nio.file.*;

// public class Main {

//     public static void main(String[] args) {

//         try {

//             // Read program.mc
//             String source =
//                     new String(
//                             Files.readAllBytes(
//                                     Paths.get(
//                                             "program.mc"
//                                     )
//                             )
//                     );

//             System.out.println(
//                     "Source code read successfully!"
//             );

//             // Lexer
//             Lexer lexer =
//                     new Lexer(source);

//             List<Token> tokens =
//                     lexer.tokenize();

//             System.out.println(
//                     "Lexical analysis completed!"
//             );

//             // Parser
//             Parser parser =
//                     new Parser(tokens);

//             List<Statement> program =
//                     parser.parse();

//             System.out.println(
//                     "Parsing completed successfully!"
//             );

//             // Semantic Analyzer
//             SemanticAnalyzer analyzer =
//                     new SemanticAnalyzer();

//             analyzer.analyze(program);

//             System.out.println(
//                     "Semantic analysis completed!"
//             );

//             // Generate C code
//             CCodeGenerator generator =
//                     new CCodeGenerator();

//             String generatedCode =
//                     generator.generate(program);

//             // Save output.c
//             Files.write(
//                     Paths.get("output.c"),
//                     generatedCode.getBytes()
//             );

//             System.out.println(
//                     "output.c created successfully!"
//             );

//             // Compile C to EXE
//             ProcessBuilder processBuilder =
//                     new ProcessBuilder(
//                             "gcc",
//                             "output.c",
//                             "-o",
//                             "output.exe"
//                     );

//             processBuilder.inheritIO();

//             Process process =
//                     processBuilder.start();

//             int exitCode =
//                     process.waitFor();

//             if (exitCode == 0) {

//                 System.out.println(
//                         "output.exe created successfully!"
//                 );

//             } else {

//                 System.out.println(
//                         "GCC compilation failed!"
//                 );
//             }

//         }

//         catch (Exception e) {

//             System.out.println(
//                     "\nCOMPILER ERROR:"
//             );

//             System.out.println(
//                     e.getMessage()
//             );
//         }
//     }
// }





import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {

        try {

            System.out.println(
                    "============================================"
            );

            System.out.println(
                    "          MINI C COMPILER"
            );

            System.out.println(
                    "============================================"
            );


            // ========================================
            // STEP 1: READ SOURCE CODE
            // ========================================

            System.out.println(
                    "\n========== STEP 1: SOURCE CODE ==========\n"
            );

            String source =
                    new String(
                            Files.readAllBytes(
                                    Paths.get("program.mc")
                            )
                    );

            System.out.println(source);


            // ========================================
            // STEP 2: LEXICAL ANALYSIS
            // ========================================

            System.out.println(
                    "\n========== STEP 2: TOKENS GENERATED ==========\n"
            );

            Lexer lexer =
                    new Lexer(source);

            List<Token> tokens =
                    lexer.tokenize();

            System.out.printf(
                    "%-20s %-20s%n",
                    "TOKEN TYPE",
                    "VALUE"
            );

            System.out.println(
                    "----------------------------------------"
            );

            for (Token token : tokens) {

                if (token.type != TokenType.EOF) {

                    System.out.printf(
                            "%-20s %-20s%n",
                            token.type,
                            token.value
                    );
                }
            }

            System.out.println(
                    "\nLexical Analysis Completed Successfully!"
            );


            // ========================================
            // STEP 3: PARSING
            // ========================================

            Parser parser =
                    new Parser(tokens);

            List<Statement> program =
                    parser.parse();

            System.out.println(
                    "\nParsing Completed Successfully!"
            );


            // ========================================
            // PRINT PARSE TREE / AST
            // ========================================

            ASTPrinter astPrinter =
                    new ASTPrinter();

            astPrinter.printProgram(program);


            // ========================================
            // STEP 4: SEMANTIC ANALYSIS
            // ========================================

            System.out.println(
                    "\n========== STEP 4: SEMANTIC ANALYSIS ==========\n"
            );

            SemanticAnalyzer analyzer =
                    new SemanticAnalyzer();

            analyzer.analyze(program);

            System.out.println(
                    "Semantic Analysis Completed Successfully!"
            );

            System.out.println(
                    "No duplicate variables found."
            );

            System.out.println(
                    "No undeclared variables found."
            );


            // ========================================
            // STEP 5: CODE GENERATION
            // ========================================

            System.out.println(
                    "\n========== STEP 5: C CODE GENERATION ==========\n"
            );

            CCodeGenerator generator =
                    new CCodeGenerator();

            String generatedCode =
                    generator.generate(program);

            Files.write(
                    Paths.get("output.c"),
                    generatedCode.getBytes()
            );

            System.out.println(
                    "Generated C Code:\n"
            );

            System.out.println(generatedCode);

            System.out.println(
                    "output.c created successfully!"
            );


            // ========================================
            // STEP 6: GCC COMPILATION
            // ========================================

            System.out.println(
                    "\n========== STEP 6: GCC COMPILATION ==========\n"
            );

            System.out.println(
                    "Compiling output.c using GCC..."
            );

            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "gcc",
                            "output.c",
                            "-o",
                            "output.exe"
                    );

            processBuilder.inheritIO();

            Process process =
                    processBuilder.start();

            int exitCode =
                    process.waitFor();


            // ========================================
            // STEP 7: EXECUTABLE CREATED
            // ========================================

            if (exitCode == 0) {

                System.out.println(
                        "\n========== STEP 7: COMPILATION SUCCESS ==========\n"
                );

                System.out.println(
                        "output.exe created successfully!"
                );

                System.out.println(
                        "\n============================================"
                );

                System.out.println(
                        "   MINI C COMPILATION COMPLETED SUCCESSFULLY"
                );

                System.out.println(
                        "============================================"
                );

                System.out.println(
                        "\nRun the executable using:"
                );

                System.out.println(
                        ".\\output.exe"
                );

            } else {

                System.out.println(
                        "\nGCC compilation failed!"
                );
            }

        }

        catch (Exception e) {

            System.out.println(
                    "\n============================================"
            );

            System.out.println(
                    "             COMPILER ERROR"
            );

            System.out.println(
                    "============================================"
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}