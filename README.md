# Contour Map Pathfinding and Searching Algorithm Visualizer

<p> <b>Visualize the most famous graph search/traversal algorithms on a grid with height values</b></p>
<p> <b>Find the shortest path from a starting point to ending point with respect to distance and height</b></p>

<h3>Visualization of Algorithms:</h3>
<ul>
  <li>Depth First Search</li>
  <li>Breadth First Search</li>
  <li>Dijkstra's shortest path algorithm</li>
  <li><b>NOTE: </b>DFS and BFS are unweighted so heights of squares are ignored</li>
  <li><b>NOTE: </b>Dijkstra's Algorithm ignores negative weights so height of square <1 are ignored</li>
</ul>

<h3>Instructions to run</h3>
<ul>
  <li>Clone this repository</li>
  <li>Navigate to folder with cloned repo</li>
  <li>Open file 'AlgorithmVisualizer.jar'</li>
</ul>

<h3>Tutorial: </h3>
<ul>
  <li>Click on 'Wall' button then select anywhere on the grid to create barriers for search</li>
  <li>Click on 'Root' button then select anywhere on the grid to create a starting point for the algorithm</li>
  <li>Click on 'Destination' button then select anywhere on the grid to create destinations for the algorithm</li>
  <li>Click on 'Eraser' button then select anywhere on the grid to remove any Walls, Roots, or Destinations</li>
  <li>Click on 'Raise Hill' button then select anywhere on the grid to create a hill on the grid</li>
  <li>Click on 'Lower Hill' button then select anywhere on the grid to create a valley on the grid</li>
  <li>Click on 'Raise Height' button then select anywhere on the grid to increase the height of selected square</li>
  <li>Click on 'Lower Height' button then select anywhere on the grid to decrease the height of selected square</li>
  <li>Click on 'Erase Height' button then select anywhere on the grid to reset the height of selected square to 1</li>
  <li>Choose Algorithm from dropdown</li>
  <li>Click 'Start' to begin search</li>
  <li>Click 'Reset' to clear board</li>
</ul>

<h3>Results:</h3>
<ul>
  <li>Animation outlining how selected algorithm will work</li>
  <li>Grey squares showing the squares that the algorithm checked</li>
  <li>Yellow path showing the path the algorithm took to find destination</li>
</ul>
<br>
<b>Created using Java with Swing GUI</b>
