package nl.tubby.aoc;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.file.Path;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

public class CliDirectoryParser {

    final Dir root = new Dir(null, "");
    Dir currentFolder;

    static Dir parse(Path path) {
        var parser = new CliDirectoryParser();
        var slurper = new Slurper<>(parser::parse);
        slurper.count(path);
        return parser.root;
    }

    Boolean parse(String line) {
        if(StringUtils.startsWith(line,"$ ")) {
            parseCommand(StringUtils.substringAfter(line,"$ "));
        }
        parseFile(line);
        return true;
    }

    private void parseCommand(String instruction) {
        String[] commandAndArgs = StringUtils.split(instruction," ",2);
        String command = StringUtils.substringBefore(instruction," ");
        String args = commandAndArgs.length>1?commandAndArgs[1]:null;
        if("cd".equals(command)) {
            if("/".equals(args)) {
                this.currentFolder = this.root;
            } else if("..".equals(args)) {
                this.currentFolder = this.currentFolder.getParent();
            } else {
                this.currentFolder = Objects.requireNonNull(this.currentFolder.get(args),args+" not found in "+this.currentFolder);
            }
        }
    }

    private void parseFile(String dirOrFile) {
        String[] elements = StringUtils.split(dirOrFile," ",2);
        if(NumberUtils.isDigits(elements[0])) {
            this.currentFolder.addFile(NumberUtils.toInt(elements[0]));
        } else if("dir".equals(elements[0])) {
            this.currentFolder.addDir(elements[1]);
        }
    }
}
class Dir {
    private final Dir parent;
    private final String name;
    private int size;
    private final SortedMap<String,Dir> files = new TreeMap<>();


    public Dir(Dir parent,String name) {
        this.parent = parent;
        this.name = name;
    }

    public Dir getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    private Stream<Dir> stream() {
        return this.files.values().stream();
    }

    Dir get(String name) {
        return this.files.get(name);
    }

    void addDir(String name) {
        if(!this.files.containsKey(name)) {
            this.files.put(name,new Dir(this,name));
        }
    }

    void addFile(Integer size) {
        this.size+=size;
    }

    public int getSize() {
        return this.size+stream().mapToInt(Dir::getSize).sum();
    }

    @Override
    public String toString() {
        String me = super.toString();
        return this.parent!=null?this.parent+"/"+me:me;
    }

    Stream<Dir> dirs() {
        return Stream.concat(Stream.of(this),stream()
                .flatMap(Dir::dirs));
    }
}