package inzh.llm.service.gguf;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class GGUFFile {

    public static class Header {

        private String magic;
        private int version;
        private long tensor_count;
        private long metadata_kv_count;
        private Map<String, Object> metadata_kvs;

        public String getMagic() {
            return magic;
        }

        public int getVersion() {
            return version;
        }

        public long getTensorCount() {
            return tensor_count;
        }

        public long getMetadataCount() {
            return metadata_kv_count;
        }

        public Map<String, Object> getMetadatas() {
            return metadata_kvs;
        }
    }

    public static class TensorInfo {

        private String name;
        private int n_dimensions;
        private long[] dimensions;
        private int type;
        private long offset;
    }

    private String name;
    private String filename;
    private long size;
    
    private Header header;
    
    public String getName() {
        return name;
    }
    
    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GGUFFile other = (GGUFFile) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static class Parser {

        public GGUFFile parse(Path path) throws IOException {
            return parse(path, true);
        }
        
        public GGUFFile parse(Path path, Boolean full) throws IOException {
            try (InputStream is = new BufferedInputStream(Files.newInputStream(path), 8192 * 60)) {
                String magic_number = nextString(is, 4);
                if (!magic_number.equals("GGUF")) {
                    throw new IOException("invalid GGUF format");
                }

                Header h = new Header();
                h.magic = magic_number;
                h.version = nextInt(is);
                h.tensor_count = nextLong(is);
                h.metadata_kv_count = nextLong(is);
                
                Map<String, Object> metadata_kvs = new HashMap<>();
                for (int i = 0; i < h.metadata_kv_count; i++) {
                    String key = nextKey(is);
                    Object value = nextValue(is);
                    if(!full && value instanceof List){
                        key += ".count";
                        value = ((List) value).size();
                    }
                    metadata_kvs.put(key, value);
                }
                h.metadata_kvs = metadata_kvs;
                
                GGUFFile f = new GGUFFile();
                f.filename = path.getFileName().toString();
                f.name = f.filename.substring(0, f.filename.length() - 5);
                f.size = Files.size(path);
                f.header = h;
                
                return f;
            }
        }

        // Native 
        public ByteBuffer nextBuffer(InputStream is, int size) throws IOException {
            byte[] bs = is.readNBytes(size);
            ByteBuffer bb = ByteBuffer.wrap(bs);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            return bb;
        }

        public short nextShort(InputStream is, int size) throws IOException {
            return nextBuffer(is, size).getShort();
        }

        public int nextInt(InputStream is) throws IOException {
            return nextInt(is, 4);
        }

        public int nextInt(InputStream is, int size) throws IOException {
            return nextBuffer(is, size).getInt();
        }

        public long nextLong(InputStream is) throws IOException {
            return nextBuffer(is, 8).getLong();
        }

        public boolean nextBoolean(InputStream is) throws IOException {
            return nextBuffer(is, 1).array()[0] == 1;
        }

        public float nextFloat(InputStream is) throws IOException {
            return nextFloat(is, 4);
        }

        public float nextFloat(InputStream is, int size) throws IOException {
            return nextBuffer(is, size).getFloat();
        }

        public String nextString(InputStream is, int size) throws IOException {
            byte[] b_str = is.readNBytes(size);
            return new String(b_str);
        }

        // GGUF Format
        public int nextSize(InputStream is) throws IOException {
            return nextInt(is, 8);
        }

        public String nextKey(InputStream is) throws IOException {
            return nextString(is, nextSize(is));
        }

        public Object nextValue(InputStream is) throws IOException {
            return nextValue(is, nextInt(is));
        }

        public Object nextValue(InputStream is, int type) throws IOException {
            switch (type) {
                case 0:
                    return nextShort(is, 1);
                case 1:
                    return nextShort(is, 1);
                case 2:
                    return nextShort(is, 2);
                case 4:
                    return nextInt(is, 4);
                case 5:
                    return nextInt(is, 4);
                case 6:
                    return nextFloat(is);
                case 7:
                    return nextBoolean(is);
                case 8:
                    return nextString(is, nextSize(is));              case 9: // Array
                    int arrayType = nextInt(is);
                    int count = nextInt(is, 8);

                    List ls = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        ls.add(nextValue(is, arrayType));
                    }
                    return Collections.unmodifiableList(ls);
                case 10:
                    return nextLong(is);
                case 11:
                    return nextLong(is);
                case 12:
                    return nextFloat(is, 8);
                default:
                    throw new IOException("stop " + type);
  
            }
        }
    }
}
