package ntut.csie.sslab.account.projects.query.adapter.presenter;

import ntut.csie.sslab.account.projects.command.entity.Repository;
import ntut.csie.sslab.account.projects.command.entity.Role;

import java.util.*;
import java.util.stream.Collectors;

public class DomainToDtoMapper {

    public static List<RepositoryDto> sortRepoDtoByOwnerNameAndRepoName(List<Repository> repositories){
        Map<String, List<RepositoryDto>> repositoryDict = new HashMap<>();
        for(Repository repository: repositories){
            if(!repositoryDict.containsKey(repository.getOwnerName()))
                repositoryDict.put(repository.getOwnerName(), new ArrayList<>());
            repositoryDict.get(repository.getOwnerName())
                    .add(new RepositoryDto(repository.getOwnerName(), repository.getRepositoryName()));
        }

        repositoryDict.forEach((k, v) -> {
            repositoryDict.put(k, v.stream().sorted(Comparator.comparing((t)->{
                return t.getRepositoryName();
            })).collect(Collectors.toCollection(ArrayList::new)));
        });

        List<String> keys = new ArrayList<>(repositoryDict.keySet());
        Collections.sort(keys);
        List<RepositoryDto> sortedRepositoryDtos = new ArrayList<>();
        for(String key: keys){
            sortedRepositoryDtos.addAll(repositoryDict.get(key));
        }
        return sortedRepositoryDtos;
    }

    public static List<MemberDto> sortMemberDtoByRoleAndusername(Map<String, Role> projectMembers){
        Map<String, List<MemberDto>> memberDict = new HashMap<>();
        projectMembers.forEach((k, v)->{
            if(!memberDict.containsKey(v.toString()))
                memberDict.put(v.toString(), new ArrayList<>());
            memberDict.get(v.toString())
                    .add(new MemberDto(k, v.toString()));
        });
        List<MemberDto> sortedMemberDtos = new ArrayList<>();
        sortedMemberDtos.addAll(
                memberDict.get(Role.PROJECT_MANAGER.toString()).stream().sorted(Comparator.comparing((t)->{
                    return t.getUsername();
                })).collect(Collectors.toCollection(ArrayList::new))
        );
        if(memberDict.containsKey(Role.PROJECT_USER.toString())) {
            sortedMemberDtos.addAll(
                    memberDict.get(Role.PROJECT_USER.toString()).stream().sorted(Comparator.comparing((t) -> {
                        return t.getUsername();
                    })).collect(Collectors.toCollection(ArrayList::new))
            );
        }

        return sortedMemberDtos;

    }
}
