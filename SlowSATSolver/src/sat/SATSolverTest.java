package sat;

import static org.junit.Assert.*;

import org.junit.Test;

import sat.env.*;
import sat.formula.*;

import java.util.ArrayList;


public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();


    
    // the main method that reads the .cnf file and calls SATSolver.solve to determine the satisfiability
    public static void main(String[] args) {
        SATSolverTest tester = new SATSolverTest();

        // test cases
        tester.testSATSolver1();
        tester.testSATSolver2();
        tester.testSATSolver3();
        tester.testSATSolver4();



//
//
//        System.out.println("Solving Sequence started");
//        System.out.println("Parsing file...");
//        long parseStarted = System.nanoTime();
//
//
////        ArrayList<Formula> listOfFormulas = AwesomeCnfParser.parseFile("src/sat/largeSat.cnf");
//        ArrayList<Formula> listOfFormulas = slowCnfParser.parseFile("src/sat/largeUnsat.cnf");
////        ArrayList<Formula> listOfFormulas = AwesomeCnfParser.parseFile("src/sat/s8Sat.cnf");
////        ArrayList<Formula> listOfFormulas = AwesomeCnfParser.parseFile("src/sat/aim-50-1_6-yes1-4.cnf");
////        ArrayList<Formula> listOfFormulas = AwesomeCnfParser.parseFile("src/sat/generated2SAT.cnf");
//        long parseTime = System.nanoTime();
//        long parseTimeTaken = parseTime - parseStarted;
//
//        System.out.println("Parsing completed in " + parseTimeTaken/1000000.0 + "ms");
//        System.out.println("Solving formulas...");
//
//
//
//        // Apparently all the test cases have only one clause per file
//        // So no need to iterate
//        Formula testFormula = listOfFormulas.get(0);
//        long started = System.nanoTime();
//        Environment e = SATSolver.solve(testFormula);
//        long time = System.nanoTime();
//        long timeTaken = time - started;
//
//        System.out.println("Solving completed in " + timeTaken/1000000.0 + "ms");
    }
    
    @Test
    public void testSATSolver1(){
        // (a v b)
        Environment e = SATSolver.solve(makeFm(makeCl(a,b))    );
        assertTrue( "one of the literals should be set to true",
                Bool.TRUE == e.get(a.getVariable())  
                || Bool.TRUE == e.get(b.getVariable())    );
        
        
    }

    @Test
    public void testSATSolver2(){
        // (~a)
        Environment e = SATSolver.solve(makeFm(makeCl(na)));
        assertEquals( Bool.FALSE, e.get(na.getVariable()));
        
    }

    @Test
    public void testSATSolver3() {
        Formula f = makeFm(makeCl(a,b,c),makeCl(a, b));
        Environment e = SATSolver.solve(f);
        assertTrue("One set to true", Bool.TRUE == e.get(a.getVariable()) || Bool.TRUE == e.get(b.getVariable()));
    }

    @Test
    public void testSATSolver4() {
        Formula f = makeFm(makeCl(na),makeCl(a));
        Environment e = SATSolver.solve(f);
    }
    
    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
//        System.out.println(f);
        return f;

    }
    
    private static Clause makeCl(Literal... e) {
        Clause c = new Clause();
        for (Literal l : e) {
            c = c.add(l);
        }
        return c;
    }

}