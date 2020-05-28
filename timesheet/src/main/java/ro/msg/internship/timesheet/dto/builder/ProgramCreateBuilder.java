package ro.msg.internship.timesheet.dto.builder;

import org.json.JSONArray;
import org.json.JSONObject;
import ro.msg.internship.timesheet.dto.ProgramCreateDto;
import ro.msg.internship.timesheet.model.Program;
import ro.msg.internship.timesheet.model.Psp;
import ro.msg.internship.timesheet.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class ProgramCreateBuilder {
    public static Program getEntityFromDto(ProgramCreateDto programCreateDto){
        JSONArray array = new JSONArray(programCreateDto.getPsps());
        Set<Psp> psps = new HashSet<>();
        for(int i = 0; i < array.length(); i++){
            JSONObject object = (JSONObject)array.get(i);
            psps.add(Psp.builder().name(object.getString("name")).build());
        }
        Set<User> users = new HashSet<>();
        array = new JSONArray(programCreateDto.getUsers());
        for(int i = 0; i < array.length(); i++){
            JSONObject object = (JSONObject)array.get(i);
            users.add(
                    User.builder()
                            .firstName(object.getString("firstName"))
                            .lastName(object.getString("lastName"))
                            .username(object.getString("username"))
                            .password(object.getString("password"))
                            .build());
        }
        return Program.builder()
                .name(programCreateDto.getName())
                .programId(programCreateDto.getProgramId())
                .startDate(LocalDate.parse(programCreateDto.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse(programCreateDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .workingHours(programCreateDto.getWorkingHours())
                .psps(psps)
                .users(users)
                .build();
    }
}
