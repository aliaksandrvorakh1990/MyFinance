package by.vorakh.training.my_finance.validation;

import java.io.File;

public class FileValidator {

    private FileValidator () {}
    
    public static boolean isExistedFile(File file) {
	    boolean isExisted = false;
	    if (file != null) {
	        isExisted = file.exists() && file.isFile();
	    }
	    return isExisted;
    }

}
