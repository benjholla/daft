package daft.sat;

public enum LogicalState {
    TRUE, FALSE, UNBOUND;

	public LogicalState not() {
        if (this == UNBOUND) {
        	return UNBOUND;
        } else if (this == TRUE) {
        	return FALSE;
        } else {
        	return TRUE;
        }
    }
	
    public LogicalState and(LogicalState other) {
        if (this == UNBOUND || other == UNBOUND) { 
        	return UNBOUND;
        } else if (this == TRUE && other == TRUE) {
        	return TRUE;
        } else {
        	return FALSE;
        }
    }

    public LogicalState or(LogicalState other) {
        if (this == UNBOUND || other == UNBOUND) {
        	return UNBOUND;
        } else if (this == TRUE || other == TRUE) {
        	return TRUE;
        } else {
        	return FALSE;
        }
    }

    public static LogicalState fromAssignment(boolean assignment) {
        return assignment ? TRUE : FALSE;
    }
}
