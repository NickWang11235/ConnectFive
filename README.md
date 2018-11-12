# ConnectFive

## Version Control for Developers -10/12/2018
 
For future commits:
  * Any commit that changes the internal logics should be followed by a detailed commit  message that describes in detail what feature
    are modified, and should start with "CORE:".
  * Any commit that does not change the internal logics should have a commit message that is brief, and should not start with "CORE".
 
Development from now on should follow the below branching protocal:
  * The git repo will now have two branches at all times: master and development. They should not be deleted under any conditions.
  * The master branch will only contain bug-free, completed code. Any version on this branch should be considered final. Merge to
    master should only occur when features are completely implemented, additionally any merge to master should be complimented by a 
    new tag.
  * The development branch will contain work-in-progress. Any version on this branch should be compile-time error free, completed,
    but with non-final features. Merge to development branch should only occur when code is finished but yet to be tested for 
    compatibility and run-time errors.
  * Any addition, deletion, or modification of code should be completed on a seperate branch that branched off the development branch.
    The seperate branch can be modified at will by any developers, any not pushed to the server unless needed for collaboration, and 
    deleted afterwards. 
  * All bugs should be recorded in the Issuelog.txt file and branched off to be modified.
  * A graph is provided below to visualize the process.

<pre>
    (initla code)										 (version 1)
	master 						----> 					   master
	     \										  	    /(merge)
      (branch)\   (initial code)   	       (incompatibility issue found)  /--->   	development/
	       \   development  	 	---->	development	 ----/	  (updated compatible code)
	            \    \			      /   /	   \		            /
                     \    \(branch)	     (merge) /   /	    \(branch)	           /(merge)
	      (branch)\    \     mapReader ---->    /   /(merge)     \	 Issue1  ---->    /
		       \(development and debugging)    /	    (fix compatibility issue)
		        \                             /
		         \    playerMovement ---->   /
		           (development and debugging)
</pre>

## How to pull using netbeans

We recommend using the official Git client instead of the following method, which is specific to NetBeans IDE.
* Official Git client: https://git-scm.com/downloads
* Official Git tutorial: https://git-scm.com/book/en/v2

  1.Click on "Clone or Download" (Green button).\
  2.Copy the URL.\
  3.Create a new project,preferably named "JavaMayhem".\
  4.Look for "Team" tab in NetBeans. It should be located on the top left corner.\
  5.In the drop down menu select Git --> Initialize Repository.\
  6.After initilizing repository, goto "Team" again, but it should look different this time. Select Remote --> Pull.\
  7.Select the remote branch, in this case,  "master" , and pull. You may need to enter your Git username and password.
  
  ## Contributors
* Nick Wang (nickwang11235@gmail.com)
