package ee.vr.cardgame.model;

import ee.vr.cardgame.entity.Card;
import lombok.Data;

@Data // tagataustal @Getter @Setter @NoArgsConstructor
public class GuessResponse {
    private String message;
    private int score;
    private Card card;
}
