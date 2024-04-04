package personal.MapleChenX.im.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import personal.MapleChenX.im.model.dto.UserDTO;

@Mapper
public interface UserMapper extends BaseMapper<UserDTO> {

}
