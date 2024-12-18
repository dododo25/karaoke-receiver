package com.dododo.receiver.controller;

import com.dododo.receiver.controller.dto.TokenRequestDTO;
import com.dododo.receiver.converter.GameModeConverter;
import com.dododo.receiver.holder.SessionsHolder;
import com.dododo.receiver.holder.TokensHolder;
import com.dododo.receiver.model.BasicInfo;
import com.dododo.receiver.model.GameMode;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Convert;
import java.util.List;

@RestController
@AllArgsConstructor
public class AdminPartsController {

    private final SessionsHolder sessionsHolder;

    private final TokensHolder tokensHolder;

    @PostMapping(value = "/join")
    public ResponseEntity<Object> join(@RequestBody RequestCodeDataDTO dto) {
        String token = tokensHolder.get(dto.code);
        HttpSession session = sessionsHolder.get(token);

        if (session == null) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("connected", true);

        return ResponseEntity.ok(new JSONObject().put("token", token).toString());
    }

    @PostMapping(value = "/select")
    public ResponseEntity<Void> select(@RequestBody @Validated(BasicInfo.class) RequestGameModeDTO dto) {
        HttpSession session = sessionsHolder.get(dto.token);

        if (session == null) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("gameMode", dto.gameMode);
        session.setAttribute("trackId", dto.trackId);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/answers")
    public ResponseEntity<Void> answers(@RequestBody RequestAnswersDTO dto) {
        HttpSession session = sessionsHolder.get(dto.token);

        if (session == null) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("answers", dto.values);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<Void> refresh(@RequestBody TokenRequestDTO dto) {
        HttpSession session = sessionsHolder.get(dto.getToken());

        if (session == null) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("refreshed", true);

        return ResponseEntity.ok().build();
    }

    @Setter
    public static class RequestCodeDataDTO {

        private String code;

    }

    @Setter
    public static class RequestGameModeDTO {

        @NotEmpty(message = "Invalid token value", groups = BasicInfo.class)
        private String token;

        @Convert(converter = GameModeConverter.class)
        private GameMode gameMode;

        @NotNull(message = "Invalid trackId value", groups = BasicInfo.class)
        private Integer trackId;
    }

    @Setter
    public static class RequestAnswersDTO {

        @NotEmpty(message = "Invalid token value", groups = BasicInfo.class)
        private String token;

        @NotEmpty(groups = BasicInfo.class)
        private List<String> values;
    }
}