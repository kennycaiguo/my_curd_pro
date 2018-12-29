import com.google.common.base.Joiner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Use {
    public static void main(String[] args) {
        String n = "smile.png', 'smiley.png', 'laughing.png', 'blush.png', 'heart_eyes.png', 'smirk.png', 'flushed.png', 'grin.png', 'wink.png', 'kissing_closed_eyes.png', 'stuck_out_tongue_winking_eye.png', 'stuck_out_tongue.png', 'sleeping.png', 'worried.png', 'expressionless.png', 'sweat_smile.png', 'cold_sweat.png', 'joy.png', 'sob.png', 'angry.png', 'mask.png', 'scream.png', 'sunglasses.png', 'heart.png', 'broken_heart.png', 'star.png', 'anger.png', 'exclamation.png', 'question.png', 'zzz.png', 'thumbsup.png', 'thumbsdown.png', 'ok_hand.png', 'punch.png', 'v.png', 'clap.png', 'muscle.png', 'pray.png', 'skull.png', 'trollface.png";
        String[] nAry = n.split("', '");
        System.out.println(nAry.length);
        for(String nItem : nAry){
            System.out.println(nItem);
        }
        List<String> x = Arrays.asList(nAry);

        String path = "E:\\idea_work\\my_curd_pro\\src\\main\\webapp\\static\\image\\emoji";
        File dir = new File(path);
        File[] files =  dir.listFiles();
        List<String> names = new ArrayList<>();
        for(File file : files){
            if(!x.contains(file.getName())){
                file.delete();
            }
        }
    }
}
