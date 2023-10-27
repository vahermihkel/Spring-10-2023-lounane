package ee.rebecca.hilowgame.model;

import ee.rebecca.hilowgame.entity.Card;
import lombok.Data;

@Data // tagataustal @Getter @Setter @NoArgsConstructor
public class GuessResponse {
    private String message;
    private int score;
    private Card card;
}
