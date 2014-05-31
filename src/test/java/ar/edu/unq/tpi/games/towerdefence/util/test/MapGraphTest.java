package ar.edu.unq.tpi.games.towerdefence.util.test;



import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.VerboseMockitoJUnitRunner;

import ar.edu.unq.tpi.games.towerdefence.graphs.MapGraph;

@RunWith(VerboseMockitoJUnitRunner.class)
public class MapGraphTest {
	
	@Test
	public void obtainVerticalStep(){
		MapGraph<String> mapGraph = new MapGraph<String>(45,50,800,500);
		double expectedVerticalStep = (double)500/(double)45;
		double actualVerticalStep = mapGraph.obtainVerticalStep();
		assertEquals(expectedVerticalStep, actualVerticalStep,0);
	}
	
}
