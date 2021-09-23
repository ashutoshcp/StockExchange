package bowling;

import java.util.ArrayList;
import java.util.List;

public final class BowlingGameScoreBoard implements BowlingGame {

    private final        List<Frame> frames;
    private static final int         MAX_FRAMES             = 10;
    private static final int         MAX_PINS               = 10;
    private static final int         MAX_ATTEMPTS_PER_FRAME = 2;
    private              int         frameCounter           = 0;
    private              int         strikeCounter          = 0;
    private static final int         ALL_STRIKE_SCORE       = 300;

    public BowlingGameScoreBoard() {
        frames = new ArrayList<>(MAX_FRAMES);
        for (int i = 0; i < MAX_FRAMES; i++) {
            frames.add(new Frame());
        }
    }

    @Override
    public void roll(int pins) {
        if (pins > MAX_PINS) {
            throw new BowlingException("illegal argument " + pins);
        }
        Frame frame = getFrame();
        if (frame == null) {
            throw new BowlingException("start new game");
        }
        frame.setScore(pins);
        if (isBonusFrame()) {
            Frame prev = getPreviousFrame();
            if (prev.isSpare()) {
                frame.limitToOneAttempt();
            }
        }
    }

    private Frame getFrame() {
        Frame frame = getCurrentFrame();
        if (frame.isDone()) {
            if (isLastFrame() && (frame.isSpare() || frame.isStrike())) {
                Frame bonus = new Frame();
                frames.add(bonus);
                frameCounter++;
                return bonus;
            }
            frameCounter++;
            if (frameCounter == MAX_FRAMES || isBonusFrame()) {
                return null;
            }
            frame = getCurrentFrame();
        }
        return frame;
    }

    @Override
    public int score() {
        int score;
        if (frameCounter == 0) {
            Frame curr = getCurrentFrame();
            return curr.score();
        }
        else {
            if (isLastFrame() && isAllStrikes()) {
                return  ALL_STRIKE_SCORE;
            }
            Frame currentFrame = getCurrentFrame();
            Frame previous = getPreviousFrame();
            if (isBonusFrame()) {
                return previous.score() +currentFrame.score();
            }
            score = currentFrame.score();
            if (previous.isSpare()) {
                score += (previous.score() + currentFrame.getFirstScore());
            }
            if (previous.isStrike()) {
                score += (previous.score() + currentFrame.getFirstScore() + currentFrame.getSecondScore());
            }
        }
        return score;
    }

    private boolean isAllStrikes() {
        return strikeCounter == MAX_FRAMES;
    }

    private boolean isLastFrame() {
        return frameCounter == MAX_FRAMES - 1;
    }

    private Frame getCurrentFrame() {
        return frames.get(frameCounter);
    }

    private boolean isBonusFrame() {
        return frames.size() > MAX_FRAMES;
    }

    private Frame getPreviousFrame() {
        return frames.get(frameCounter-1);
    }

    private class Frame {
        private final int[] scores   = new int[MAX_ATTEMPTS_PER_FRAME];
        private       int   noOfPins = 10;
        private int     noAttempts = 0;
        private boolean isStrike   = false;

        private boolean isSpare() {
            return noOfPins==0 && noAttempts==MAX_ATTEMPTS_PER_FRAME && !isStrike;
        }

        private boolean isStrike() {
            return noOfPins == 0 && noAttempts==MAX_ATTEMPTS_PER_FRAME && isStrike;
        }

        private boolean isDone() {
            return noAttempts == MAX_ATTEMPTS_PER_FRAME;
        }

        private void setScore(int score) {
            scores[noAttempts++] = score;
            noOfPins -= score;
            if (score == MAX_PINS) {
                isStrike = true;
                strikeCounter++;
            }
        }

        private void limitToOneAttempt() {
            scores[1] = 0;
            noAttempts++;
        }

        private int score() {
            return scores[0] + scores[1];
        }

        private int getFirstScore() {
            return scores[0];
        }

        private int getSecondScore() {
            return scores[1];
        }
    }

    private class BowlingException extends RuntimeException {
        BowlingException(String message) {
            super(message);
        }
    }
}
