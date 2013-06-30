package org.common.service.classification;

import static org.common.classification.ClassificationUtil.COMMERCIAL;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.common.classification.ClassificationData;
import org.common.spring.CommonContextConfig;
import org.gplus.spring.GplusContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommonContextConfig.class, GplusContextConfig.class })
public class ClassificationServiceLiveTest {

    @Autowired
    private ClassificationService classificationService;

    // tests

    @Test
    public final void whenContextIsBootstrapped_thenNoException() {
        //
    }

    @Test
    public final void whenReadingClassificationTrainingFile_thenNoException() throws IOException {
        final List<String> lines = IOUtils.readLines(new BufferedReader(new FileReader("src/main/resources/classification/commercial.classif")));
        System.out.println(lines);
    }

    @Test
    public final void whenTweetIsClassified_thenNoException() {
        final boolean isCommercial = classificationService.isCommercial("URGENT: Scala Developer | 3 Month Contract | Westminster | Immediate Requirement! #Scala #Freelance #Jobs #IT");
        assertTrue(isCommercial);
    }

    @Test
    public final void givenClassifierWasTrained_whenUsingThePersistedToDisk_thenNoExceptions() throws IOException {
        final List<ImmutablePair<String, String>> testData = ClassificationData.commercialAndNonCommercialTweets();
        int correct = 0;
        int total = 0;
        for (final Pair<String, String> tweetData : testData) {
            total++;
            final boolean expected = COMMERCIAL.equals(tweetData.getLeft());
            final boolean isTweetCommercial = classificationService.isCommercial(tweetData.getRight());
            if (isTweetCommercial == expected) {
                correct++;
            }
        }

        final double cd = correct;
        final double td = total;
        System.out.println(cd / td);
    }
}
