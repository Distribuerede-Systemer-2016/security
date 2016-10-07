import java.security.MessageDigest;

public class Digester {

  private final static String SALT = "n0zaCTADRUuTb@JUp01n%5@(l@IAaLlZ";
  private static MessageDigest digester;

  static {
    try {
      digester = MessageDigest.getInstance("MD5");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hash string with MD5 hashing
   * @param str
   * @return MD5 hash of string
   */
  public static String hash(String str) {
    if (str == null || str.length() == 0) {
      throw new IllegalArgumentException("Error");
    }
    return Digester._hash(str);
  }

  public static String hashWithSalt(String str){
    if (str == null || str.length() == 0) {
      throw new IllegalArgumentException("Error");
    }

    str = str + Digester.SALT;

    return Digester._hash(str);
  }

  private static String _hash(String str){
    digester.update(str.getBytes());
    byte[] hash = digester.digest();
    StringBuffer hexString = new StringBuffer();
    for (byte aHash : hash) {
      if ((0xff & aHash) < 0x10) {
        hexString.append("0" + Integer.toHexString((0xFF & aHash)));
      } else {
        hexString.append(Integer.toHexString(0xFF & aHash));
      }
    }
    return hexString.toString();
  }

}
