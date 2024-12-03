package nl.tubby.aoc22;

import nl.tubby.Resource;
import nl.tubby.Slurper;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CliDirectoryParser {
    private static final Pattern SPACE = Pattern.compile("\s");

    final Dir root = new Dir(null, "");
    Dir currentFolder;

    static Dir parse(Resource resource) {
        var parser = new CliDirectoryParser();
        var slurper = new Slurper<>(parser::parse);
        slurper.count(resource);
        return parser.root;
    }

    Boolean parse(String line) {
        String[] splitted = SPACE.split(line,3);
        if("$".equals(splitted[0])) {
            parseCommand(splitted[1],splitted.length>2?splitted[2]:null);
        } else {
            parseFile(splitted[0],splitted[1]);
        }
        return true;
    }

    private void parseCommand(String command,String args) {
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

    private void parseFile(String sizeOrDir,String name) {
        if("dir".equals(sizeOrDir)) {
            this.currentFolder.addDir(name);
        } else {
            this.currentFolder.addFile(Integer.parseInt(sizeOrDir));
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

    void addFile(int size) {
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