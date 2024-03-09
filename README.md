# Coursework Specification

1. Key Information

• This coursework is a combination of individual and group components. Both are compulsory to be completed.
• The first part of this coursework is an individual component, where all students must complete the deliverables independently and submit it separately.
• The second part of the coursework is a group work component, where a designated group of students work to complete the deliverables together.
• Please refer to the coursework submission links in Canvas to find the due date for both the individual as well as group components of the coursework.
• Java is the programming language for developing the system specified in the coursework.

2. System to be Developed.

   2.1 System Introduction

   The system to be developed is a virtual board game – but it does not have an elaborate graphical user interface.
   Instead, the game is to be played via the console mode of the IDE platforms. The system will also have its distinctive theme, 'save
   our planet', based on caring for the planet. Think of investing in different
   environmentally friendly schemes, developing various aspects of these, generating
   income from them or devoting resources to them. Rather than use cash for your
   transactions, you might want to think about allocating some other resources –
   people, equipment, know-how, time-and-effort. It's your job to think through and
   apply the 'metaphor' you want to use in your game.
   The simple console-based interface allows you to concentrate on the process of
   determining and designing the underlying object-oriented system rather than
   focusing on visual or audio effects. The system uses English phrases to convey the
   game state and ask its players what they want to do next. Though you don't have
   to develop a speech user interface (SUI), imagine a game where the state of play
   can be conveyed in words alone – whether a new development is being reported or
   the current state of play summarised.
   The emphasis in this project is on the process of requirements analysis, system
   design, software implementation and system testing that delivers reliable and
   appropriate functionality. In addition, the project will demonstrate your
   understanding of and ability to practice object-oriented software engineering
   principles and work in a software engineering team (both in individual and group
   capacity).

   2.2 UML Modelling Requirements

   The system requirements, analysis and design components of the system should
   be presented and documented in the graphical notation known as the Unified
   Modelling Language (UML). The project will be a 'use case driven' development
   process, in which each use case describes "a set of sequences of actions, including
   variants, that a system performs to yield an observable result of value to an actor"
   (Booch, Rumbaugh and Jacobson).
   It is important to note that each use case is represented as a single ellipse, and
   each use case is a complete set of sequences of actions. For example, a single use
   case might describe everything related to registering a group of players to start
   the game play in the 'save our planet game. That might include a set of steps like
   entering the names of the player in a sequence, checking if the two players have
   identical names, checking if the number of registered players is within the range
   of numbers of players permitted at one time to play the game, etc. (please note:
   you might design the use case to register the players for gameplay in differently
   in your Save Our Planet Game!). To remind you again, all those steps/activities
   involved in registering the player for gameplay are represented by a single use
   case and, therefore, a single ellipse.
   Your system (i.e., save our planet board game) might have several such use cases
   to document the entire functioning, based on your system requirements and
   analysis activity. Each of those identified use cases might have some associations
   with the system actors (typically the users of the system) and many other
   relationships with other use cases to depict the flow of activities taking place
   within the system to achieve the system's goal as specified in the system
   specifications. That collection of the use cases (in the form of eclipse) with actors
   (as stick person), associations (as solid lines between actor and use cases) and inter
   use case relationships (as dashed lines) is called the use case diagram collectively.
   This is a key piece of work that you will produce to capture the entire system in a
   snapshot. Additionally, an ellipse (representing single use case) in a use case
   diagram contains the name of a use case.
   After developing the appropriate use case for the system, the corresponding use
   case description describes what the sequence (flow) of actions would be to achieve
   a desirable outcome, which is the whole point of the use case! For example, if
   registering a set of players use cases executes successfully, the players will have
   become a registered player for the gameplay – that is the desirable outcome.
   However, the description of the registering a set of players use case should also
   say what alternative sequences (or flows) are needed at steps under certain
   circumstances – what happens, for instance if identical names were entered for
   the two players for the same gameplay. In other words, the use case descriptions
   convey much more information that the individual use cases in the use case
   diagram may not be able to document.
   As explained above, several use cases (several ellipses) will typically appear in a
   single use case diagram. Each ellipse represents a single use case; therefore, each
   use case must have a use case description.
   Please note:
   • Never use a use-case ellipse to represent a single step in a chain or sequence of
   steps. A single ellipse is a set of sequences of steps – including the normal flow
   as well as the alternative flows – that achieves some important outcomes!
   • As the use case is used for representing an important part of the functioning of
   the overall system, you should demonstrate rigour in identifying, shortlisting,
   and finalising the use cases for the final use case diagram of the system.
   Creating a large number of use cases with no obvious significance is therefore
   discouraged. There are no bonus points for a large number of use cases in the
   form of a jumble of ellipses in use case diagram. Rather, it might impact the
   quality of your work and so impact negatively in your score.

3. System Requirements

   Your game will have many of the features of a board game but in a much simpler
   form. Within the constraints of the 'natural language interface', your customer's
   core requirements are set out below.
   Remember, this is what your customer is asking for and expects to be delivered.
   You can realise these requirements while giving them your own creative 'twist' –
   but your customer is not asking for additional features (e.g., the customer does not
   want more squares, more types of squares, or more players than the number
   stipulated below). On the other hand, where checks are needed to ensure that the
   game is usable (for example, to avoid a situation where two players have the same
   name), such checks should be implemented, even if they are not explicitly
   requested. That is simply good design.
   Similarly, although your customer has no immediate plans to 'adapt' or 'upgrade'
   Save Our Planet to a more specialised or more complex game, there may be (OO)
   design features that, with only a little additional effort, you can incorporate
   'behind the scenes' to make your system more maintainable and extensible – e.g.
   a well-designed game would allow the number of squares or the maximum number
   of players to be increased or reduced easily in the code, should that requirement
   arise in future. Good software design not only meets current requirements but can
   easily accommodate change. [N.B. A separate user interface for significant re-
   configuration of the game is NOT one of the requirements for Save Our Planet.]
   Here are the core requirements. Your game should do the following and have the
   following features (or do/have something that is functionally/conceptually
   equivalent):

   1. The game has up to four players, and their names should be entered at the
      beginning of the gameplay.

   2. The players take turns. They throw two virtual dice at a time during their
      turn.

   3. Players are told where they have landed and their obligations or
      opportunities. Then, where appropriate, they may indicate their choice of
      action. For example, dedicating some of their resources may 'take charge' of
      a square no one else owns. If a player lands on a square no one owns, but
      they don't want to take charge of it themselves, it may be offered at the
      usual cost to another player.

   4. If a player's resources have changed, the system indicates the reason for the
      change and announces the player's new 'balance' (e.g., the 'funds' or 'credits'
      that are still available).

   5. There is a start square, where players pick up their 'resources' (it's your
      choice what the 'resources' represent – and you should be inventive with the
      square's name – this is the equivalent of a 'Collect X as you pass Go' square).
      Next, there is a square on the board where nothing happens – again, you
      decide what it is called in your game.

   6. There are four 'fields', two consisting of three 'areas' and two consisting of
      two 'areas' respectively. Therefore, a number of related areas form a 'field'
      in Save Our Planet. You decide what the fields are called and what they
      represent. For example, Renewable Energy might be a 'field'. Different
      areas make up a field: Hydroelectricity might be an area in the field of
      Renewable Energy [say it out loud and see if it makes sense!]. You decide
      what the fields are in your game and what areas they will include. One of
      the two-area fields is the costliest field on the board to acquire and resource;
      another two-area field is the least costly field to acquire and resource.

   7. Before you can develop an area within a field, you must own/manage/’be in
      charge of' (you decide what 'custodianship' means!) the whole field – and on
      your turn you can develop an area in a field that you already own even if
      you are not positioned on that area.

   8. Decide what development is called, what it represents, and how much it
      costs in your game; others might have to pay you or invest in it to use it.
      (Again, you decide the nature and the significance of the transactions
      between players.) Three 'developments' are needed before you can establish
      (and pay for, or otherwise 'resource') the equivalent of a 'major development'
      (again, you decide what this represents and what it costs).

   9. Not only is there a cost associated with developing areas within fields: when
      you land on an area but do not 'own' it yourself, you must give up some of
      your resources for it – the more developed the area, the greater the resource
      consumed.

   10. Decide what happens when one player runs out of resources. Are they the
       winner or the loser? It's your choice. If one player no longer wants to play,
       the game ends. In both cases (no resources left; not wanting to play), the
       amount of resource each player holds are shown. There is no need to convert
       'developments', etc., to an equivalent value in your 'resource units'. Find
       forms of words that express the outcome of the game in a manner
       appropriate to the overall style of your version of Save Our Planet. Is
       winning a matter of amassing resources from other players, or should our
       Save-Our-Planet hero be aiming to give all that they have (and more) to
       support the worthy ventures of a fellow eco-warrior?

4. Guide for Gameplay Style

   A game of this kind might start and unfold in the following manner (the example
   is not taken from Save Our Planet though the behaviour of your game will be
   broadly similar):

   What is the first player's name? Player A
   What is the second player's name? Player B
   <Player A>, would you like to roll? [Y/N]: Y
   OK, <Player A>, you've rolled a 5 and a 6 – that makes 11.
   You've landed on <Square X>.
   In charge of that square <no one>.
   Do you want to invest in this and take charge (Y/N): Y

   ***

   Investment made.
   Your old balance was <1500 points>.
   Your new balance is <1440 points>.
   Player A is now in charge of <Square X>
   End of <Player A> turn

   ***

   <Player B>, would you like to roll? [Y/N]: \_
   Because the game is to be conducted in a natural language only (i.e., English
   phrases that convey the state of play, etc.), the system should have fewer 'squares'
   than a traditional board game. Therefore, a separate guide is included for your
   understanding as below. The game you will develop is meant to be very, very
   simple – literally like the limited set of boxes below. The purpose of this game
   guide is to show the position and attributes of the squares. You may create your
   game guide with any suitable drawing tool and should include that in the
   Requirements Analysis section of the system documentation.
   Again, the example shown is NOT for Save Our Planet, nor is it necessarily
   complete; it is intended only to represent the way such a graphical representation
   might be drawn. Remember that, through the comments, it writes to the console,
   the system itself must remind players of their positions and their custodianship of
   squares and the properties of those squares.

5. System Deliverables

5.1 First Part: Group Deliverable (80%)

As the first part of the coursework deliverables, the coursework group should
produce a working system that meets the coursework specifications and a report
(20 pages or less, excluding the appendix) covering the following aspects of the
system.
• Requirements Engineering: Document the requirements engineering process,
including the process of requirements discovery and requirement specification.
Please follow the agile approach of requirements discovery involving the
system's features, scenarios, and user stories. In this stage, record the final
product backlog (formal specifications) after the careful classification,
prioritisation, and negotiation of the requirements for the system. Based on the
requirements discovery, please also draw a virtual game board. (20% marks).
• Designing: Develop the necessary high-level UML diagrams to document the
modelling of the system at a coarse level. This includes a high-level UML Use
Case diagram and mapping of the interactions between the different
components within the system as sequence diagrams. Also, include a high-level
class diagram reflecting only initial domain-level details. The process also
involves the user story mappings to visualise the player’s journey in a gameplay
scenario. (20% marks).
• Agile Iterations: Document iterative development by recording the sprint
plannings, selection of the sprint backlog, the development and testing of the
product increments, and the sprint cycle reviews. On an incremental basis, this
should include progress on the product backlog in the form of project burndown
charts and Gitlab logs for the activities. Please make use of the Jira scrum
boards to document this process (20%)
• The working system that satisfies the overall system requirements. A system
that satisfies all the requirements attracts up to 10% marks. In addition, up to
10% additional marks are available for systems that, within the constraints of
the text user interface, deliver the required functionality in a manner that
demonstrates excellent usability, including clear, timely and engaging
interaction with the players and a novel and coherent interpretation of the
Save Our Planet theme (though it is sometimes fun, elaborate 'pretty printing'
– i.e. making shapes, etc., out of the text – is not required for this system
[remember the SUI/’ words alone guideline above!]). (20% marks)
• In addition, teams must produce a video (with clear commentary; approx. 7
minutes) of their working system. The video should illustrate the working
implementation of the functionality requested above. The video must be in MP4
format and run in VLC software: check that it does by using the available
software at https://www.videolan.org/vlc/index.en-GB.html. This video is
required for review by the external examiner. The project cannot be marked
if the video is not submitted or cannot be played by the assessors. The video
should be uploaded to Canvas while the working system is uploaded.

5.2 Second Part: Individual Deliverable (20%)

As the second part of the coursework deliverables, every learner should produce a
short critical evaluation report (a maximum of four/five pages) covering the
following aspects of the coursework. (20% marks)
• Please detail your contribution to the coursework along with a rating for
your contribution. Also, describe what development plan and approach suit
your project.
• Provide commentary on the requirements elicitation, design, and agile
iterations aspects of the development and the development decisions made
by the team at each stage.
• Discuss the instances of good design in coursework development, especially
relating to quality indicators in the design and development of the system,
like maintainability, extensibility, security, and user experiences.
• Discuss what legal and ethical aspects might be relevant to your software
product.
