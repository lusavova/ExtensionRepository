package telerikacademy.extensionrepository.constants;

public class Constants {
    public static final String FILE_DATE_FORMAT = "yyyy_MM_dd_HH_mm_ss";
    // at least one number, one lowercase and one uppercase letter
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$";
    public static final String GITHUB_PATTERN = "^(?<remove>https:\\/\\/github\\.com\\/)(?<name>.*)";
    public static final String OAUTH_ACCESS_TOKEN = "e9e6d224388129819c52834be0999826803ce5b3";
    public static final String GITHUB_USERNAME = "lusavova";
    public static final String FILE_LOCATION = "../FILES";

    public static final String TAG_PATTERN = "^[a-zA-Z0-9_]{2,}$";
}
