package utils;

import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.util.Hashtable;  
import java.util.Properties;  
  
import org.apache.log4j.Logger;  
  
/** 
 *  
 * ��������:��̬��ȡ�����ļ����������� 
 * <p> 
 * ��Ȩ���У� 
 * <p> 
 * δ������˾��ɣ��������κη�ʽ���ƻ�ʹ�ñ������κβ��� 
 *  
 * @author dengcd �������ڣ�2008-10-9 
 * @author ������� �޸����ڣ�2008-10-9 
 * @since wapportal_manager version(2.0) 
 */  
public class PropertyReader {  
  
 private static Logger _log = Logger.getLogger(PropertyReader.class);  
  
 private static Hashtable<String, Properties> pptContainer = new Hashtable<String, Properties>();  
  
 /** 
  *  
  * ������;������: �������  
  *  
  * @param propertyFilePath 
  *            �����ļ�(������·��) 
  * @param key 
  *            ���Լ� 
  * @return ����ֵ 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static String getValue(String propertyFilePath, String key) {  
  Properties ppts = getProperties(propertyFilePath);  
  return ppts == null ? null : ppts.getProperty(key);  
 }  
  
 /** 
  *  
  * ������;������: ��������ļ���Key����Ӧ��ֵ  
  *  
  * @param propertyFilePath 
  *            �����ļ�·��(������·�����ļ�ϵͳ���ļ�·��) 
  * @param key 
  *            ���Եļ� 
  * @param isAbsolutePath 
  *            �Ƿ�Ϊ����·��:true|false�����Ǳ����ļ�ϵͳ·�������磺C:/test.propreties��<br> 
  * <br> 
  *            <b>ע��</b>����ͨ����·������ȡ�������ļ�����ֻ֪�������ļ����ļ�ϵͳ·�������ļ�ϵͳ��ַ���ô˷�������ȡ���е�Key����Ӧ��Value 
  * @return key������ֵ 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static String getValue(String propertyFilePath, String key,  
   boolean isAbsolutePath) {  
  if (isAbsolutePath) {  
   Properties ppts = getPropertiesByFs(propertyFilePath);  
   return ppts == null ? null : ppts.getProperty(key);  
  }  
  return getValue(propertyFilePath, key);  
 }  
  
 /** 
  *  
  * ������;������: ��������ļ�������  
  *  
  * @param propertyFilePath 
  *            �����ļ�(������·��) 
  * @return ���� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static Properties getProperties(String propertyFilePath) {  
  if (propertyFilePath == null) {  
   _log.error("propertyFilePath is null!");  
   return null;  
  }  
  Properties ppts = pptContainer.get(propertyFilePath);  
  if (ppts == null) {  
   ppts = loadPropertyFile(propertyFilePath);  
   if (ppts != null) {  
    pptContainer.put(propertyFilePath, ppts);  
   }  
  }  
  return ppts;  
 }  
  
 /** 
  *  
  * ������;������: ��������ļ�������  
  *  
  * @param propertyFilePath 
  *            �����ļ�·��(������·�����ļ�ϵͳ·��) 
  * @return �����ļ����� Properties 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static Properties getPropertiesByFs(String propertyFilePath) {  
  if (propertyFilePath == null) {  
   _log.error("propertyFilePath is null!");  
   return null;  
  }  
  Properties ppts = pptContainer.get(propertyFilePath);  
  if (ppts == null) {  
   ppts = loadPropertyFileByFileSystem(propertyFilePath);  
   if (ppts != null) {  
    pptContainer.put(propertyFilePath, ppts);  
   }  
  }  
  return ppts;  
 }  
  
 /** 
  *  
  * ������;������: ��������  
  *  
  * @param propertyFilePath 
  *            �����ļ�(������·��) 
  * @return ���� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 private static Properties loadPropertyFile(String propertyFilePath) {  
  java.io.InputStream is = PropertyReader.class  
    .getResourceAsStream(propertyFilePath);  
  if (is == null) {  
   return loadPropertyFileByFileSystem(propertyFilePath);  
  }  
  Properties ppts = new Properties();  
  try {  
   ppts.load(is);  
   return ppts;  
  } catch (Exception e) {  
   _log.debug("���������ļ�����:" + propertyFilePath, e);  
   return null;  
  }  
 }  
  
 /** 
  *  
  * ������;������: ���ļ�ϵͳ���������ļ�  
  *  
  * @param propertyFilePath 
  *            �����ļ�(�ļ�ϵͳ���ļ�·��) 
  * @return ���� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 private static Properties loadPropertyFileByFileSystem(  
   final String propertyFilePath) {  
  try {  
   Properties ppts = new Properties();  
   ppts.load(new java.io.FileInputStream(propertyFilePath));  
   return ppts;  
  } catch (java.io.FileNotFoundException e) {  
   _log.error("FileInputStream(\"" + propertyFilePath  
     + "\")! FileNotFoundException: " + e);  
   return null;  
  } catch (java.io.IOException e) {  
   _log.error("Properties.load(InputStream)! IOException: " + e);  
   return null;  
  }  
 }  
  
 /** 
  *  
  * ������;������: �Դ��ڵ������ļ�����Ӽ�ֵ�Բ����� 
  *  
  * @param propertyFilePath 
  *            �����ļ���·��(������·�����ļ�ϵͳ·��) 
  * @param htKeyValue 
  *            ��ֵ��Hashtable 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean setValueAndStore(String propertyFilePath,  
   java.util.Hashtable<String, String> htKeyValue) {  
  return setValueAndStore(propertyFilePath, htKeyValue, null);  
 }  
  
 /** 
  *  
  * ������;������: �Դ��ڵ������ļ�����Ӽ�ֵ�Բ�����  
  *  
  * @param propertyFilePath 
  *            �����ļ���·��(������·�����ļ�ϵͳ·��) 
  * @param htKeyValue 
  *            ��ֵ��Hashtable 
  * @param storeMsg 
  *            ����ʱ��ӵĸ�����Ϣ��ע�ͣ� 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean setValueAndStore(String propertyFilePath,  
   java.util.Hashtable<String, String> htKeyValue, String storeMsg) {  
  Properties ppts = getProperties(propertyFilePath);  
  
  if (ppts == null || htKeyValue == null) {  
   return false;  
  }  
  ppts.putAll(htKeyValue);  
  java.io.OutputStream stream = null;  
  try {  
   stream = new java.io.FileOutputStream(propertyFilePath);  
  } catch (FileNotFoundException e) {  
   _log.debug("propertyFilePath = " + propertyFilePath);  
   String path = PropertyReader.class.getResource(propertyFilePath)  
     .getPath();  
   _log.debug("~~~~~~~~path~~~XXX~~~~~" + path);  
   try {  
    stream = new java.io.FileOutputStream(path);  
   } catch (FileNotFoundException e1) {  
    _log.error("FileNotFoundException! path=" + propertyFilePath);  
    return false;  
   }  
  }  
  
  if (stream == null) {  
   return false;  
  }  
  
  try {  
   ppts.store(stream, storeMsg != null ? storeMsg  
     : "set value and store.");  
   return true;  
  } catch (java.io.IOException e) {  
   e.printStackTrace();  
   return false;  
  } finally {  
   if (stream != null) {  
    try {  
     stream.close();  
    } catch (IOException e) {  
     e.printStackTrace();  
    }  
   }  
  }  
 }  
  
 /** 
  *  
  * ������;������: ���������ļ�  
  *  
  * @param propertyFilePath 
  *            Ҫ�洢�����ļ���·�� 
  * @param htKeyValue 
  *            �����ļ��еļ�ֵ��Hashtable 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean createPropertiesFile(String propertyFilePath,  
   java.util.Hashtable<String, String> htKeyValue) {  
  java.io.File file = new java.io.File(propertyFilePath);  
  if (!file.exists()) {  
   try {  
    file.createNewFile();  
   } catch (java.io.IOException e) {  
    e.printStackTrace();  
   }  
  }  
  return setValueAndStore(propertyFilePath, htKeyValue,  
    "create properties file:" + file.getName());  
 }  
  
 /** 
  *  
  * ������;������:��������ֵ  
  *  
  * @param propertyFilePath 
  *            �����ļ�(������·��) 
  * @param key 
  *            ���Լ� 
  * @param value 
  *            ����ֵ 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean setValue(String propertyFilePath, String key,  
   String value) {  
  Properties ppts = getProperties(propertyFilePath);  
  if (ppts == null) {  
   return false;  
  }  
  ppts.put(key, value);  
  return true;  
 }  
  
 /** 
  *  
  * ������;������: ���������ļ�����  
  *  
  * @param properties 
  *            �����ļ����� 
  * @param propertyFilePath 
  *            Ҫ�����·�� 
  * @param msg 
  *            ����ʱ��ӵĸ�����Ϣ��ע�ͣ� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static void store(Properties properties,  
   String propertyFilePath, String msg) {  
  try {  
   java.io.OutputStream stream = new java.io.FileOutputStream(  
     propertyFilePath);  
   properties.store(stream, msg);  
  } catch (java.io.FileNotFoundException e) {  
   _log.error("FileOutputStream(" + propertyFilePath  
     + ")! FileNotFoundException: " + e);  
  } catch (java.io.IOException e) {  
   _log.error("store(stream, msg)! IOException: " + e);  
   e.printStackTrace();  
  }  
 }  
  
 /** 
  *  
  * ������;������: ɾ������ֵ  
  *  
  * @param propertyFilePath 
  *            �����ļ�(������·��) 
  * @param key 
  *            ���Լ� 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static String removeValue(String propertyFilePath, String key) {  
  
  Properties ppts = getProperties(propertyFilePath);  
  if (ppts == null) {  
   return null;  
  }  
  return (String) ppts.remove(key);  
 }  
  
 /** 
  *  
  * ������;������: ɾ�������ļ��е�Key��������Ӧ�ļ�ֵ��  
  *  
  * @param propertyFilePath 
  *            �����ļ�·��(������·�����ļ�ϵͳ·��) 
  * @param key 
  *            key���� 
  * @return �����ļ����� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static Properties removeValue(String propertyFilePath,  
   String[] key) {  
  if (key == null) {  
   _log.error("key[] is null!");  
   return null;  
  }  
  Properties ppts = getProperties(propertyFilePath);  
  if (ppts == null) {  
   return null;  
  }  
  for (String strKey : key) {  
   ppts.remove(strKey);  
  }  
  return ppts;  
 }  
  
 /** 
  *  
  * ������;������:ɾ�������ļ��е�Key��������Ӧ�ļ�ֵ�ԣ����������ļ�����־û��������棩 
  *  
  *  
  * @param propertyFilePath 
  *            �����ļ�·��(������·�����ļ�ϵͳ·��) 
  * @param key 
  *            �����ļ��е�key���� 
  * @return �ɹ����true|false�� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean removeValueAndStore(String propertyFilePath,  
   String[] key) {  
  Properties ppts = removeValue(propertyFilePath, key);  
  if (ppts == null) {  
   return false;  
  }  
  store(ppts, propertyFilePath, "batch remove key value!");  
  return true;  
 }  
  
 /** 
  *  
  * ������;������: ����ָ��·���������ļ��еļ�����Ӧ��ֵ  
  *  
  * @param propertyFilePath 
  *            �����ļ�·��(������·�����ļ�ϵͳ·��) 
  * @param key 
  *            �����ļ��е�key 
  * @param newValue 
  *            Ҫ���µ���ֵ 
  * @return �ɹ����true|false�� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean updateValue(String propertyFilePath,  
   String key, String newValue) {  
  if (key == null || newValue == null) {  
   _log.error("key or newValue is null!");  
   return false;  
  }  
  java.util.Hashtable<String, String> ht = new java.util.Hashtable<String, String>();  
  ht.put(key, newValue);  
  return setValueAndStore(propertyFilePath, ht, "update " + key  
    + "'s value!");  
 }  
  
 /** 
  *  
  * ������;������: ��������ָ��·���������ļ��еļ�����Ӧ��ֵ  
  *  
  * @param propertyFilePath 
  *            �����ļ�·��(������·�����ļ�ϵͳ·��) 
  * @param htKeyValue 
  *            Ҫ���µļ�ֵ��Hashtable 
  * @return �ɹ����true|false�� 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static boolean batchUpdateValue(String propertyFilePath,  
   java.util.Hashtable<String, String> htKeyValue) {  
  if (propertyFilePath == null || htKeyValue == null) {  
   return false;  
  }  
  return setValueAndStore(propertyFilePath, htKeyValue,  
    "batch update key value!");  
 }  
  
 /** 
  *  
  * ������;������: �Ƴ����ص������ļ�  
  *  
  * @param propertyFilePath 
  *            �����ļ�(������·��) 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static Properties removePropertyFile(String propertyFilePath) {  
  
  return pptContainer.remove(propertyFilePath);  
 }  
  
 /** 
  *  
  * ������;������: ���¼���ĳ��Property�ļ�  
  *  
  * @param propertyFilePath 
  *            Ҫ���¼��ص�Property�ļ��������ǰ�ڴ���û�еĻ�����أ������滻 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static void reloadPropertyFile(String propertyFilePath) {  
  pptContainer.remove(propertyFilePath);  
  loadPropertyFile(propertyFilePath);  
 }  
  
 /** 
  *  
  * ������;������: ��������ļ���·��  
  *  
  * @param pkg 
  *            ���� 
  * @param propertyFileName 
  *            �����ļ��� 
  * @return 
  * @author dengcd �������ڣ�2008-10-9 
  * @author ������� �޸����ڣ�2008-10-9 
  * @since wapportal_manager version(2.0) 
  */  
 public final static String getPpropertyFilePath(String pkg,  
   String propertyFileName) {  
  
  pkg = pkg == null ? "" : pkg.replaceAll("\\.", "/");  
  propertyFileName = propertyFileName.endsWith(".properties") ? propertyFileName  
    : (propertyFileName + ".properties");  
  return "/" + pkg + "/" + propertyFileName;  
 }  
  
 public static void main(String[] args) {  
  String path = "/config/jdbc.properties";  
  String v = PropertyReader.getValue(path, "jdbc.driverClassName");  
  _log.info("value0 = " + v);  
    
    
  Hashtable<String, String> ht = new Hashtable<String, String>();  
  ht.put("name", "dengcd");  
  PropertyReader.setValueAndStore(path, ht);  
  String v_ = PropertyReader.getValue(path, "name");  
  _log.info("value1 = " + v_);  
  PropertyReader.reloadPropertyFile(path);  
  String v2_ = PropertyReader.getValue(path, "name");  
  _log.info("value2 = " + v2_);  
 }  
}  