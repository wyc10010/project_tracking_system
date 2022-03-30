//package ntut.csie.sslab.account.application.springboot.web.security;
//
////import ntut.csie.sslab.account.users.command.adapter.repository.UserMapper;
//import ntut.csie.sslab.account.users.command.adapter.repository.UserRepositoryPeer;
//import ntut.csie.sslab.account.users.command.usecase.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService{
//    private UserRepository userRepository;
//
////    @Autowired
////    public void setUserRepository(UserRepository userRepository){
////        this.userRepository = userRepository;
////    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        return UserMapper.transformToData(userRepository.getUserByUsername(username).get());
//        return null;
//    }
//
//    public UserDetails loadUserByUserId(String userId) {
////        return UserMapper.transformToData(userRepository.findById(userId).get());
//        return null;
//    }
//}
