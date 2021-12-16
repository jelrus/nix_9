package csvio;

import csvio.util.ObjectConverter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CSVReaderWriter {

    public static String[] getHeader(Object obj) throws InvocationTargetException, NoSuchMethodException,
                                                        InstantiationException, IllegalAccessException {
        return ObjectConverter.getObjectFields(obj.getClass());
    }

    public static void createRecord(String path, Object obj, ArrayList<?> objects)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        File file = new File(path);
        file.createNewFile();
        ArrayList<String[]> arrayObjects = new ArrayList<>();
        arrayObjects.add(getHeader(obj));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Object object : objects) {
                arrayObjects.add(object.toString().split(", "));
            }
            writeArrayObjectsToFile(bw, obj, arrayObjects);
        }
    }

    private static void writeArrayObjectsToFile(BufferedWriter bw, Object obj, ArrayList<String[]> lines) throws IOException {
        if (!lines.contains(obj.toString().split(", "))) {
            int counter = 0;
            for (String[] line : lines) {
                for (int i = 0; i < line.length; i++) {
                    if (i == line.length - 1) {
                        bw.write(line[i]);
                    } else {
                        bw.write(line[i]);
                        bw.write(",");
                    }
                }
                counter++;
                if (counter < lines.size()) {
                    bw.write("\n");
                }
            }
        }
    }

    public static ArrayList<?> readAllObjects(String path, Class<?> cls) throws IOException, InvocationTargetException,
                                                                                NoSuchMethodException, InstantiationException,
                                                                                IllegalAccessException {
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<String[]> arrayObjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                arrayObjects.add(br.readLine().split(","));
            }
        }
        for (String[] lines : arrayObjects) {
            if (arrayObjects.indexOf(lines) != 0) {
                Object result = ObjectConverter.toObject(cls, arrayObjects.get(arrayObjects.indexOf(lines)), arrayObjects.get(0));
                objects.add(result);
            }
        }
        return objects;
    }
}