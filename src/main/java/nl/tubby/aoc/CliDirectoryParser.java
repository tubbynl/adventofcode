package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CliDirectoryParser {

    private Path root = null;
    private Path currentFolder;

    Boolean parse(String line) {
        if(StringUtils.startsWith(line,"$ ")) {
            return parseCommand(StringUtils.substringAfter(line,"$ "));
        }
        return false;
    }

    private Boolean parseCommand(String instruction) {
        String[] commandAndArgs = StringUtils.split(instruction," ",2);
        String command = StringUtils.substringBefore(instruction," ");
        String args = commandAndArgs.length>1?commandAndArgs[1]:null;
        if("cd".equals(command)) {
            if("/".equals(args)) {

            }
        } else if("ls".equals(command)) {
            return true;
        }
        return false;
    }
}

class File {
    final String name;
    String size;

    public File(String name) {
        this.name = name;
    }

}
class Dir extends File {
    final List<File> files = new ArrayList<>();

    public Dir(String name) {
        super(name);
    }
}