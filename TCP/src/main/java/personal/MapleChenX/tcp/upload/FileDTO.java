package personal.MapleChenX.tcp.upload;

import lombok.Data;

@Data
public class FileDTO {
    private int cmd;
    private String fileName;
    private byte[] fileContent;
}
