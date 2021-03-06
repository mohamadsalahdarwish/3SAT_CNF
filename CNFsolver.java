import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CNFsolver {
	
	CnfSatInstance CNF = null;

	CNFsolver(CnfSatInstance CNF){
		this.CNF = CNF;
		CNF.initialise();
	}
	
	public static void main(String[] args){
		
//		CNFsolver test = new CNFsolver(CNFparser.parseDimacsCnfFile("src/cnf.txt"));
//		System.out.println(test.getCNF());
//		test.getCNF().initialise();
//		for(int i : test.getCNF().getKnownAssignments())
//			System.out.print(i +",");
//		System.out.println("");
//		int[] guess = test.getCNF().getKnownAssignments();
//		for(int i = 0; i < guess.length; i++){
//			if (guess[i] == 0)
//				guess[i] = test.getCNF().randomAssignment1();
//		}
//		
//		for(int i : guess)
//			System.out.print(i + ",");
//		System.out.println("");
//		
//		System.out.println(test.verifyAssignment(guess));
	}

	public boolean verifyAssignment(int[] assignment){
		if(CNF.getNumVars() != assignment.length){
			 System.err.println("Error. Assignment does not contain the required number of variables");
			 return false;
		}
		else{
			int[] satisfiedClauses = new int[CNF.getNumClauses()];
			
			for(int i = 0; i < assignment.length; i++){
				if(assignment[i] == 1){ //if i is true
					for(int j: CNF.getOccurrenceMap()[i]){ //check the list in the occurrence map corresponding to i
						satisfiedClauses[j-1] = 1; //set the appropriate entry in satisfiedClauses (the entry j - 1corresponding to the int in occurenceMap[i]) to 1
					}
				}
				if(assignment[i] == -1){
					for(int j: CNF.getOccurrenceMap()[i + CNF.getNumVars()]){ //check the list in the occurrence map corresponding to -i
						satisfiedClauses[j-1] = 1; //set the appropriate entry in satisfiedClauses (the entry j - 1corresponding to the int in occurenceMap[i + numVars]) to 1
					}
					
				}
			}
			
			for (int i : satisfiedClauses){
				if (i != 1)
					return false;
			}
			return true;
		}
	}

	/**
	 * If there is an unsatisfied clause, returns the first unsatisfied clause found.
	 * Returns 0 if all are satisfied, -1 if error
	 * @param assignment
	 * @return
	 */
	public int unsatisfiedClause(int[] assignment){
		if(CNF.getNumVars() != assignment.length){
			 System.err.println("Error. Assignment does not contain the required number of variables");
			 return -1;
		}
		else{
			int[] satisfiedClauses = new int[CNF.getNumClauses()];
			
			for(int i = 0; i < assignment.length; i++){
				if(assignment[i] == 1){ //if i is true
					for(int j: CNF.getOccurrenceMap()[i]){ //check the list in the occurrence map corresponding to i
						satisfiedClauses[j-1] = 1; //set the appropriate entry in satisfiedClauses (the entry j - 1corresponding to the int in occurenceMap[i]) to 1
					}
				}
				if(assignment[i] == -1){
					for(int j: CNF.getOccurrenceMap()[i + CNF.getNumVars()]){ //check the list in the occurrence map corresponding to -i
						satisfiedClauses[j-1] = 1; //set the appropriate entry in satisfiedClauses (the entry j - 1corresponding to the int in occurenceMap[i + numVars]) to 1
					}
					
				}
			}
			
			for (int i = 0; i < satisfiedClauses.length; i++){
				if (satisfiedClauses[i] != 1)
					return i + 1;
			}
			return 0;
		}

	}

	public CnfSatInstance getCNF() {
		return CNF;
	}
	
	/**
	 * Generates a random permutation of variables from 1 to n for PPSZ
	 * @param numVars
	 * @return
	 */
	public int[] permutatePi(int numVars){
		
		List<Integer> preRet = new LinkedList<Integer>();
		for(int i = 1; i <= numVars; i++)
			preRet.add(i);
		Collections.shuffle(preRet);
		int[] ret = new int[preRet.size()];
		for(int i = 0; i < preRet.size(); i++)
			ret[i] = preRet.get(i);
		return ret;
	}
	
	/**Generates a random vector of n -1s and 1s
	 * 
	 */
	public int[] randomVector(int numVars){
		int[] ret = new int[numVars];
		for(int i = 0; i < numVars; i++){
			ret[i] = Math.random() > 0.5 ? 1 : -1;
		}
		return ret;
	}

	/**
	 * Similar to the function CnfSatInstance.givenVar() but operates on a given clause
	 * and its corresponding occurrenceMap instead of a given SAT instance. Housekeeping
	 * of the known assignments is done by the caller function
	 * @param var
	 * @param numVars
	 * @param occurrenceMap
	 * @param clauses
	 * @return
	 */
    public List<List<Integer>> givenVar(Integer var, int numVars, List<Integer>[] occurrenceMap, List<List<Integer>> clauses){
    	int givenVariable = (int) var > 0 ? (int) var : Math.abs((int) var) + numVars;
    		
    	LinkedList<List<Integer>> clausesToRemove = new LinkedList<List<Integer>>();
    	LinkedList<List<Integer>> copyOfClauses = new LinkedList<List<Integer>>(clauses);
    	
    	for(int i = 1; i <= occurrenceMap[givenVariable - 1].size(); i++){
    		clausesToRemove.add(clauses.get(occurrenceMap[givenVariable - 1].get(i - 1) - 1));
    	}
    	
    	for(List<Integer> l : clausesToRemove)
    		copyOfClauses.remove(l);
    	
    	int negGivenVariable = givenVariable > numVars ? givenVariable - numVars : -givenVariable;
    	
    	for(List<Integer> l : clauses){
    		for(int i = 1; i <= l.size(); i++){
    			if((int)l.get(i - 1) == negGivenVariable){
    				l.remove(i - 1);
    			}
    		}
    	}    	
    	return copyOfClauses;
    }
    
    public List<Integer>[] computeOccurrenceMap(List<List<Integer>> clauses, int numVars){
    	@SuppressWarnings("unchecked")
		LinkedList<Integer>[] occurrenceMap = new LinkedList[numVars * 2];
    	for (int i = 0; i < numVars * 2; i++)
    	{
    		occurrenceMap[i] = new LinkedList<Integer>();
    	}
    	for (int i = 1; i <= clauses.size(); i++)
    	{
    		List<Integer> clause = clauses.get(i-1);
    		for (int literal : clause)
    		{
    			int pos = literal;
    			if (literal < 0) pos = numVars + Math.abs(literal);
    			occurrenceMap[pos-1].add(i);
    		}
    	}
    	return occurrenceMap;
    }

    /**
     * Performs the PPSZ search algorithm to find a satisfying assignment
     */

	/**
	 * Performs an iteration of the Modify procedure of
	 * PPSZ
	 * @param CNF
	 * @param pi
	 * @param initialAssignment
	 * @return
	 */
	public int[] PPSZmodify(int[] initialAssignment){
		int numVars = CNF.getNumVars();
		int[] pi = permutatePi(numVars);
		List<List<Integer>> currentClauses = CNF.getClauses();
		List<Integer>[] occurrenceMap = CNF.getOccurrenceMap();
		int[] assignments = new int[numVars];
		int[] knownAssignments = CNF.getKnownAssignments();
		
		for (int i = 0; i < knownAssignments.length; i++) //copying of array to prevent modification of knownAssignments array
			assignments[i] = knownAssignments[i];
		
		for(int i : pi){
			if(assignments[i-1] == 0){ //if the entry in the array of assignments is not already assigned
				if(occurrenceMap[i - 1].size() == 1)
					assignments[i-1] = 1;
				else if(occurrenceMap[i + numVars - 1].size() == 1)
					assignments[i-1] = -1;
				else
					assignments[i-1] = initialAssignment[i-1];
				currentClauses = givenVar(assignments[i-1] * i, numVars, occurrenceMap, currentClauses);
				occurrenceMap = computeOccurrenceMap(currentClauses, numVars);
			}
		}
		
		return assignments;
	}
	
	/**
	 * Implements the Schoning random walk algorithm for solving 3-SAT.
	 * Assumes that the initial assignment already takes into account the
	 * known assignments.
	 * Returns the satisfying assignment
	 */
	public int[] randomWalk(int[] initialAssignment){
		for (int i = 0; i < initialAssignment.length; i ++){
			if(CNF.getKnownAssignments()[i] != 0)
				initialAssignment[i] = CNF.getKnownAssignments()[i];
		}
		
		List<List<Integer>> clauses = CNF.getClauses();
		int[] assignment = new int[initialAssignment.length] ;
		for(int i = 0; i < initialAssignment.length; i++)
			assignment[i] = initialAssignment[i];
		
		for(int i = 0; i <= 3 * CNF.getNumVars(); i++){
			if(!verifyAssignment(assignment)){
				System.out.println(clauses.get(unsatisfiedClause(assignment) - 1).size());
				int posToFlip = new Random().nextInt(clauses.get(unsatisfiedClause(assignment) - 1).size());
				int varToFlip = Math.abs(clauses.get(unsatisfiedClause(assignment) - 1).get(posToFlip));
				if(CNF.getKnownAssignments()[varToFlip - 1] == 0)
					assignment[varToFlip - 1] = -assignment[varToFlip - 1];
			}
			else
				break;
		}
		return assignment;
	}
}
