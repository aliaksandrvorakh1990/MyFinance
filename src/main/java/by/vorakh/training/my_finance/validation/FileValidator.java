package by.vorakh.training.my_finance.validation;

import java.io.File;

public interface FileValidator {
    public static String UNCORRECT_FILE = "File is not exist or is not a file.";
    
    default  boolean isExistedFile(File file) {
	    boolean isExisted = false;
	    if (file != null) {
	        isExisted = file.exists() && file.isFile();
	    }
	    return isExisted;
    }

}
