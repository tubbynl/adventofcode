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
            return parseCommand(StringUtils.substringAfter(line,"$ "));
        }
        return parseFile(line);
    }

    private Boolean parseCommand(String instruction) {
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
            return true;
        } else if("ls".equals(command)) {
            return true;
        }
        return false;
    }

    private Boolean parseFile(String dirOrFile) {
        String[] elements = StringUtils.split(dirOrFile," ",2);
        if(NumberUtils.isDigits(elements[0])) {
            this.currentFolder.addFile(elements[1],NumberUtils.toInt(elements[0]));
        } else if("dir".equals(elements[0])) {
            this.currentFolder.addDir(elements[1]);
        } else {
            return false;
        }
        return true;
    }
}

class File {
    private final Dir parent;
    private final String name;
    private final Integer size;

    public File(Dir parent,String name, Integer size) {
        this.parent = parent;
        this.name = name;
        this.size = size;
    }

    public Dir getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public String toString() {
        return getName();
    }
}
class Dir extends File {
    final SortedMap<String,File> files = new TreeMap<>();

    public Dir(Dir parent,String name) {
        super(parent,name, null);
    }

    private Stream<File> stream() {
        return this.files.values().stream();
    }

    <F extends File> F get(String name) {
        return (F)this.files.get(name);
    }
    Dir addDir(String name) {
        if(!this.files.containsKey(name)) {
            this.files.put(name,new Dir(this,name));
        }
        return get(name);
    }

    File addFile(String name, Integer size) {
        if(!this.files.containsKey(name)) {
            this.files.put(name,new File(this,name, size));
        }
        return get(name);
    }

    @Override
    public Integer getSize() {
        return stream().mapToInt(File::getSize).sum();
    }

    @Override
    public String toString() {
        String me = super.toString();
        return getParent()!=null?getParent().toString()+"/"+me:me;
    }

    Stream<Dir> dirs() {
        return Stream.concat(Stream.of(this),stream()
                .filter(Dir.class::isInstance)
                .map(Dir.class::cast)
                .flatMap(Dir::dirs));
    }
}