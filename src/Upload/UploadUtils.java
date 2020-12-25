package Upload;


public class UploadUtils {
    private String type;

    public String judge(String contentType) {

        switch (contentType) {
            case "image/gif":
                type = "gif";
                break;
            case "image/jpeg":
                type = "jpg";
                break;
            default:
                type = "error";
                break;
        }
        return type;
    }
}
