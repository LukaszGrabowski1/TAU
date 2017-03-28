package pl.edu.pjwstk.lab4;


import org.jbehave.core.Embeddable;
import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.junit.AnnotatedEmbedderRunner;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;


@RunWith(AnnotatedEmbedderRunner.class)
@Configure(storyControls = RingEmbedder.MyStoryControls.class, storyLoader = RingEmbedder.MyStoryLoader.class, storyReporterBuilder = RingEmbedder.MyReportBuilder.class,
        parameterConverters = { RingEmbedder.MyDateConverter.class })
@UsingEmbedder(embedder = Embedder.class,
        generateViewAfterStories = false,
        ignoreFailureInStories = true,
        ignoreFailureInView = true)
@UsingSteps(instances = { RingSteps.class })
public class StepsEmbeddable implements Embeddable {
    private Embedder embedder;

    public void useEmbedder(Embedder embedder) {
        this.embedder = embedder;
    }

    @Override
    @Test
    public void run() throws Throwable {
        embedder.runStoriesAsPaths(new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(),
                asList("**/*.story"), asList("")));
    }
}
