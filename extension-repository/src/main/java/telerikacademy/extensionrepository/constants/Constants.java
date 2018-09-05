package telerikacademy.extensionrepository.constants;

public class Constants {
    public static final String FILE_DATE_FORMAT = "yyyy_MM_dd_HH_mm_ss";
    public static final String APPROVED_USER_STATUS = "approved";
    public static final String PENDING_USER_STATUS = "pending";
    // at least one number, one lowercase and one uppercase letter
    public static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$";
    public static final String GITHUB_PATTERN = "(?<remove>https:\\/\\/github\\.com\\/)(?<name>.*)";
}
