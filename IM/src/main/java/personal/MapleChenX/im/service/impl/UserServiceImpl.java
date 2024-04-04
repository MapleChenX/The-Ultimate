package personal.MapleChenX.im.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import personal.MapleChenX.im.mapper.UserMapper;
import personal.MapleChenX.im.model.dto.UserDTO;
import personal.MapleChenX.im.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDTO> implements UserService{

}
