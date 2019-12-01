package frankdevhub.job.webauto.google.drive.ftp.adapter.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.regex.PatternSyntaxException;


/**
 * <p>Title:JarUtils.java</p>  
 * <p>Description: </p>  
 * <p>Copyright: Copyright (c) 2019</p>  
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>  
 * @author frankdevhub   
 * @date:2019-04-23 17:20
 */

public class JarUtils {

	private static Hashtable<File, HashSet<String>> cache = new Hashtable<File, HashSet<String>>();

	private JarUtils() {
	}

	public static Attributes getManifestAttributes() {
		final CodeSource codeSource = JarUtils.class.getProtectionDomain().getCodeSource();
		if (codeSource == null) {
			return null;
		}

		JarFile jar = null;
		try {
			URL url = codeSource.getLocation();
			if (url == null) {
				return null;
			}

			final String filename = url.getFile();
			File file = new File(filename);
			if (!file.exists() || !filename.endsWith("jar")) {
				return null;
			}

			jar = new JarFile(filename);
			Manifest manifest = jar.getManifest();
			return manifest.getMainAttributes();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (jar != null) {
					jar.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static void printManifestAttributesToString() {
		try {
			Attributes attrs = getManifestAttributes();
			if (attrs == null) {
				return;
			}
			for (Map.Entry<Object, Object> entry : attrs.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		} catch (Exception ex) {
			System.err.println("Error reading MANIFEST.MF: " + ex.getMessage());
		}
	}

	public static Map<String, String> getManifestAttributesAsMap() {
		try {
			Attributes attrs = getManifestAttributes();
			if (attrs == null) {
				return Collections.emptyMap();
			}
			Map<String, String> ret = new HashMap<String, String>(attrs.size());
			for (Entry<Object, Object> entry : attrs.entrySet()) {
				ret.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
			return ret;
		} catch (Exception ex) {
			System.err.println("Error reading MANIFEST.MF: " + ex.getMessage());
			return null;
		}
	}

	public static void rescan(File[] files) {
		cache.clear();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				scanDirectory(f);
			} else if (f.isFile() && f.getName().endsWith(".jar")) {
				try {
					scanJarFile(f);
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
			}
		}
	}

	private static void scanDirectory(File directory) {
		HashSet<String> classEntries = readDirectoryClassEntries(directory, "");
		cache.put(directory, classEntries);
	}

	private static void scanJarFile(File f) throws IOException, FileNotFoundException {
		HashSet<String> classEntries = readJarClassEntries(f);
		cache.put(f, classEntries);
	}

	public static HashSet<String> getClassEntries(File file) throws FileNotFoundException, IOException {
		if (cache.containsKey(file)) {
			return (HashSet<String>) cache.get(file);
		} else {
			return readClassEntries(file);
		}
	}

	public static HashSet<String> getClassEntries(File file, String regex)
			throws FileNotFoundException, IOException, PatternSyntaxException {
		HashSet<String> entries = null;
		if (cache.containsKey(file)) {
			entries = (HashSet<String>) cache.get(file);
		} else {
			entries = (HashSet<String>) readClassEntries(file);
		}
		return filter(entries, regex);
	}

	private static HashSet<String> readClassEntries(File file) throws IOException, FileNotFoundException {
		if (file.isDirectory()) {
			return readDirectoryClassEntries(file, "");
		} else if (file.isFile() && file.getName().endsWith(".jar")) {
			return readJarClassEntries(file);
		}
		return new HashSet<String>();
	}

	private static HashSet<String> readDirectoryClassEntries(File directory, String prefix) {
		HashSet<String> allClasses = new HashSet<String>();
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isFile() && f.getName().endsWith(".class")) {
				int index = f.getName().lastIndexOf(".class");
				String className = prefix + f.getName().substring(0, index);
				allClasses.add(className);
			} else if (f.isDirectory()) {
				String newPrefix = prefix + f.getName() + ".";
				HashSet<String> directoryClasses = readDirectoryClassEntries(f, newPrefix);
				allClasses.addAll(directoryClasses);
			}
		}
		return allClasses;
	}

	private static HashSet<String> filter(HashSet<String> entries, String pattern) {
		HashSet<String> result = new HashSet<String>();
		for (Iterator<String> i = entries.iterator(); i.hasNext();) {
			String entry = (String) i.next();
			if (entry.matches(pattern)) {
				result.add(entry);
			}
		}
		return result;
	}

	private static HashSet<String> readJarClassEntries(File file)
			throws FileNotFoundException, IOException, PatternSyntaxException {
		HashSet<String> set = new HashSet<String>();
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		JarInputStream is = new JarInputStream(bis);
		try {
			boolean end = false;
			while (!end) {
				JarEntry entry = is.getNextJarEntry();
				if (entry != null) {
					String entryName = entry.toString();
					if (entryName.endsWith(".class")) {
						String className = translateClassEntry(entryName);
						set.add(className);
					}
				} else {
					end = true;
				}
			}
		} finally {
			is.close();
		}
		return set;
	}

	public static Vector<String> getClassEntries(File[] files, String regex) {
		Vector<String> allEntries = new Vector<String>();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			try {
				allEntries.addAll(getClassEntries(f, regex));
			} catch (FileNotFoundException fnfex) {
			} catch (IOException ioex) {
			}
		}
		return allEntries;
	}

	private static String translateClassEntry(String entry) {
		String result = entry.replace('/', '.');
		int classIndex = result.lastIndexOf(".class");
		if (classIndex != -1) {
			return result.substring(0, classIndex);
		} else
			return result;
	}

	public static File[] getClassPath() {
		File[] classPathFiles = getClassPathFromSystemProperty();
		File[] mfClassPathFiles = null;
		if (classPathFiles.length == 1) {
			try {
				File libDirectory = classPathFiles[0].getParentFile();
				JarFile jar = new JarFile(classPathFiles[0]);
				Manifest mf = jar.getManifest();
				if (mf != null) {
					Attributes att = mf.getMainAttributes();
					if (att != null) {
						String mfClassPath = (String) att.getValue("Class-Path");
						if (mfClassPath != null) {
							String[] paths = mfClassPath.split(" ");
							mfClassPathFiles = new File[paths.length];
							for (int i = 0; i < paths.length; i++) {
								File f = new File(paths[i]);
								if (!f.isAbsolute()) {
									f = new File(libDirectory, paths[i]);
								}
								mfClassPathFiles[i] = f;
							}
						}
					}
				}
				jar.close();
			} catch (IOException ioex) {
			}
		}
		if (mfClassPathFiles != null) {
			File[] allPathFiles = new File[classPathFiles.length + mfClassPathFiles.length];
			System.arraycopy(classPathFiles, 0, allPathFiles, 0, classPathFiles.length);
			System.arraycopy(mfClassPathFiles, 0, allPathFiles, classPathFiles.length, mfClassPathFiles.length);
			return allPathFiles;
		} else {
			return classPathFiles;
		}
	}

	private static File[] getClassPathFromSystemProperty() {
		String classPath = System.getProperty("java.class.path");
		String[] paths = classPath.split(";");
		File[] files = new File[paths.length];
		for (int i = 0; i < paths.length; i++) {
			files[i] = new File(paths[i]);
		}
		return files;
	}

	public static File[] getClassPathMatching(String pattern) {
		File[] allFiles = getClassPath();
		Vector<File> matchingFiles = new Vector<File>();
		for (int i = 0; i < allFiles.length; i++) {
			File f = allFiles[i];
			if (f.getName().matches(pattern)) {
				matchingFiles.add(f);
			}
		}
		return (File[]) matchingFiles.toArray(new File[0]);
	}

}
