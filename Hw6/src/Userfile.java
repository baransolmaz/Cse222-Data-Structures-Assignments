import java.io.*;

public class Userfile {
    private File userFile;
    /**
     * Sets File
     * @param file File
     */
    public void setFile(File file){
        this.userFile=file;
    }
    /**
     * Returns User File
     * @return File
     */
    public File getUserFile() {
        return userFile;
    }
}
