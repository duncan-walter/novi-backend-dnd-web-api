package walter.duncan.dndwebapi.businessmodels.charactermanagement;

import org.springframework.web.multipart.MultipartFile;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolation;
import walter.duncan.dndwebapi.exceptions.BusinessRuleViolationException;

import java.util.List;

public class CharacterPortraitModel {
    private static List<String> allowedContentTypes = List.of("image/png", "image/jpg", "image/jpeg", "image/gif");
    private String originalFileName;
    private String storedFileName;
    private String mimeType;

    public CharacterPortraitModel(
            String originalFileName,
            String storedFileName,
            String mimeType
    ) {

        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.mimeType = mimeType;
    }

    //region Getters & Setters
    public String getOriginalFileName() {
        return this.originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoredFileName() {
        return this.storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    //endregion

    //region Static validators for outside validation (required in certain flows)
    public static void validateUpload(MultipartFile portrait) {
        var contentType = portrait.getContentType();

        if (!allowedContentTypes.contains(contentType)) {
            throw new BusinessRuleViolationException(
                    BusinessRuleViolation.CHARACTER_PORTRAIT_ILLEGAL_FILE_TYPE,
                    String.format(
                            "Illegal character portrait file type: '%s'. Please use one one of the following file types: '%s.'",
                            portrait.getContentType(),
                            String.join(", ", allowedContentTypes)
                    )
            );
        }
    }
    //endregion
}