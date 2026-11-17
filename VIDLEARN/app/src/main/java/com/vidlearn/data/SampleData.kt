package com.vidlearn.data

object SampleData {
    private val sampleVideos = listOf(
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"
    )

    private fun videoUri(index: Int): String = sampleVideos[index % sampleVideos.size]

    val categories = listOf("All", "Design", "Code", "Business", "Creative")
    val levels = listOf("All Levels", "Beginner", "Intermediate", "Advanced")

    val courses = listOf(
        Course(
            id = 1,
            icon = "layout",
            color = "#0b6b3a",
            category = "Design",
            title = "Complete Web Design Masterclass 2026",
            instructor = "Amara Okoye",
            rating = 4.8,
            ratingsCount = "18,420",
            price = "₦8,500",
            isBestseller = true,
            isHighestRated = true,
            level = "All Levels",
            duration = "6.5 total hours",
            description = "Learn colour, type, layout and motion to design modern websites people love. Master professional tools, responsive design grids, accessibility standards, and hands-on CSS styling.",
            requirements = listOf(
                "No prior design or programming knowledge needed.",
                "A web browser and an internet connection."
            ),
            targetAudience = listOf(
                "Beginner web designers wanting to build portfolio websites.",
                "Developers wanting to improve their visual design skills.",
                "Freelancers offering web design services to clients."
            ),
            instructorTitle = "Lead UI/UX Designer & Frontend Architect",
            instructorBio = "Amara is a senior UI designer with over 10 years of experience crafting award-winning web and mobile digital products.",
            instructorStudentsCount = "85,000+ Students",
            reviews = listOf(
                CourseReview("Kofi Mensah", 5.0, "2 days ago", "Incredible course! The interactive practice sandbox helped me retain CSS grid faster than any other course."),
                CourseReview("Sarah Jenkins", 4.8, "1 week ago", "Clear instructions, beautiful examples, and very engaging instructor."),
                CourseReview("Emeka Nnamdi", 5.0, "2 weeks ago", "Highly recommended for anyone looking to build professional looking websites.")
            ),
            qnaItems = listOf(
                QnAItem("q1", "Tariq A.", "3 days ago", "What is the best color contrast ratio for accessibility?", listOf("Amara Okoye: Always target WCAG AA standard (4.5:1 ratio for normal text).")),
                QnAItem("q2", "Blessing O.", "5 days ago", "Should I learn CSS Grid before Flexbox?", listOf("Amara Okoye: Both are complementary! Flexbox handles 1D layouts while Grid handles 2D layouts."))
            ),
            lessons = listOf(
                Lesson(
                    title = "Colour & contrast",
                    task = "Make the heading text red and the background black.",
                    startCode = "<h1 style=\"\">Hello world</h1>",
                    checkRegexes = listOf("color\\s*:\\s*(red|#0b6b3a)", "background[a-z-]*\\s*:\\s*(black|#000)"),
                    hint = "Use style=\"color:red;background:black\"",
                    videoUri = videoUri(0),
                    duration = "12 mins"
                ),
                Lesson(
                    title = "Typography that sells",
                    task = "Set the font-size to 32px and font-weight to bold.",
                    startCode = "<p style=\"\">Big bold text</p>",
                    checkRegexes = listOf("font-size\\s*:\\s*32px", "font-weight\\s*:\\s*(bold|700)"),
                    hint = "Add font-size:32px; font-weight:bold;",
                    videoUri = videoUri(1),
                    duration = "15 mins"
                ),
                Lesson(
                    title = "Layouts with grid",
                    task = "Create a 3 column grid container.",
                    startCode = "<div style=\"\">\n<p>1</p><p>2</p><p>3</p>\n</div>",
                    checkRegexes = listOf("display\\s*:\\s*grid", "grid-template-columns"),
                    hint = "Use display:grid; grid-template-columns:1fr 1fr 1fr;",
                    videoUri = videoUri(2),
                    duration = "18 mins"
                ),
                Lesson(
                    title = "Motion basics",
                    task = "Add a CSS transition to the button.",
                    startCode = "<button style=\"\">Hover me</button>",
                    checkRegexes = listOf("transition"),
                    hint = "Add transition: all .3s;",
                    videoUri = videoUri(3),
                    duration = "14 mins"
                )
            )
        ),
        Course(
            id = 2,
            icon = "code",
            color = "#111114",
            category = "Code",
            title = "Python for Beginners: Zero to Hero",
            instructor = "Dev Patel",
            rating = 4.7,
            ratingsCount = "52,110",
            price = "₦9,900",
            isBestseller = true,
            isHotAndNew = true,
            level = "Beginner",
            duration = "8.0 total hours",
            description = "Write real Python programs with simple, step by step video lessons. Master logic, functions, loops, and data structures while practicing code directly in the sandbox.",
            requirements = listOf(
                "No previous programming experience required.",
                "A computer running Windows, macOS, or Linux."
            ),
            targetAudience = listOf(
                "Complete programming beginners.",
                "Students wanting to learn automation & scripting.",
                "Aspiring software engineers and data analysts."
            ),
            instructorTitle = "Senior Software Engineer & Python Evangelist",
            instructorBio = "Dev is a software engineer with 12+ years of experience building scalable backend services and automated pipelines.",
            instructorStudentsCount = "120,000+ Students",
            reviews = listOf(
                CourseReview("Chidi Anagonye", 5.0, "Yesterday", "The bite-sized lessons paired with interactive code checks are genius!"),
                CourseReview("Aisha Danjuma", 4.7, "3 days ago", "Paced extremely well for total beginners.")
            ),
            qnaItems = listOf(
                QnAItem("q1", "Samuel B.", "1 day ago", "Is Python 3 backwards compatible with Python 2?", listOf("Dev Patel: Python 3 introduced breaking cleanups, so write Python 3 for modern projects!"))
            ),
            lessons = listOf(
                Lesson(
                    title = "Variables & types",
                    task = "Create a variable called name and print it.",
                    startCode = "# your code here\n",
                    checkRegexes = listOf("name\\s*=", "print\\("),
                    hint = "name = 'Ada' then print(name)",
                    videoUri = videoUri(1),
                    duration = "10 mins"
                ),
                Lesson(
                    title = "Loops and logic",
                    task = "Write a for loop that prints numbers with range.",
                    startCode = "# your code here\n",
                    checkRegexes = listOf("for\\s+\\w+\\s+in\\s+range", "print"),
                    hint = "for i in range(1, 6): print(i)",
                    videoUri = videoUri(2),
                    duration = "16 mins"
                ),
                Lesson(
                    title = "Functions",
                    task = "Define a function greet(name) that returns text.",
                    startCode = "# your code here\n",
                    checkRegexes = listOf("def\\s+greet\\s*\\(", "return"),
                    hint = "def greet(name): return 'Hi ' + name",
                    videoUri = videoUri(3),
                    duration = "15 mins"
                ),
                Lesson(
                    title = "Build a mini app",
                    task = "Ask the user for input and print a greeting.",
                    startCode = "# your code here\n",
                    checkRegexes = listOf("input\\s*\\(", "print\\s*\\("),
                    hint = "name = input('Name? ')",
                    videoUri = videoUri(4),
                    duration = "20 mins"
                ),
                Lesson(
                    title = "Next steps",
                    task = "Write a comment (starting with #) about what you will build next.",
                    startCode = "",
                    checkRegexes = listOf("#\\s*\\w+"),
                    hint = "Start the line with # and a few words",
                    videoUri = videoUri(0),
                    duration = "10 mins"
                )
            )
        ),
        Course(
            id = 3,
            icon = "dollar-sign",
            color = "#1f8f55",
            category = "Business",
            title = "Money & Investing 101",
            instructor = "Chidi Eze",
            rating = 4.6,
            ratingsCount = "9,870",
            price = "₦7,200",
            isBestseller = false,
            isHighestRated = false,
            level = "All Levels",
            duration = "4.0 total hours",
            description = "Budget, save and invest with confidence, even if you are starting from scratch. Learn practical money management, index funds, compound growth, and scam protection.",
            requirements = listOf(
                "Open mind to financial education.",
                "Basic math skills."
            ),
            targetAudience = listOf(
                "Anyone wanting to achieve financial freedom.",
                "Young professionals starting their earning journey."
            ),
            instructorTitle = "Certified Financial Planner & Investment Advisor",
            instructorBio = "Chidi has guided thousands of families and young professionals toward financial independence over the past decade.",
            instructorStudentsCount = "30,000+ Students",
            reviews = listOf(
                CourseReview("Grace Udoh", 5.0, "4 days ago", "Simple, actionable, and straight to the point!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Budget that works",
                    task = "List 3 monthly expenses, each line starting with -",
                    startCode = "- ",
                    checkRegexes = listOf("(^|\\n)\\s*-.*\\n\\s*-.*\\n\\s*-"),
                    hint = "Write three lines starting with -",
                    videoUri = videoUri(0),
                    duration = "12 mins"
                ),
                Lesson(
                    title = "Saving habits",
                    task = "Write your goal like: Save 50000 by December",
                    startCode = "",
                    checkRegexes = listOf("save\\s+\\S+.*\\bby\\b"),
                    hint = "Use the words Save ... by ...",
                    videoUri = videoUri(1),
                    duration = "14 mins"
                ),
                Lesson(
                    title = "Intro to investing",
                    task = "Write the percent of income you would invest, e.g. 20%",
                    startCode = "",
                    checkRegexes = listOf("\\d+\\s*%"),
                    hint = "Include a number with a % sign",
                    videoUri = videoUri(2),
                    duration = "18 mins"
                ),
                Lesson(
                    title = "Avoiding scams",
                    task = "List 2 scam red flags, each starting with -",
                    startCode = "- ",
                    checkRegexes = listOf("-.*\\n\\s*-"),
                    hint = "Two lines starting with -",
                    videoUri = videoUri(3),
                    duration = "15 mins"
                )
            )
        ),
        Course(
            id = 4,
            icon = "film",
            color = "#0b6b3a",
            category = "Creative",
            title = "Video Editing Fast: Reels, Shorts & More",
            instructor = "Lena Cruz",
            rating = 4.5,
            ratingsCount = "6,300",
            price = "₦6,500",
            isBestseller = false,
            isHotAndNew = true,
            level = "Beginner",
            duration = "5.2 total hours",
            description = "Cut, grade and export scroll-stopping videos in record time. Master jump cuts, sound design, vertical video formats, and color correction.",
            requirements = listOf(
                "A smartphone or computer with video editing software."
            ),
            targetAudience = listOf(
                "Content creators, YouTubers, and TikTokers.",
                "Digital marketers looking to create engaging video ads."
            ),
            instructorTitle = "Video Producer & Creator Economy Coach",
            instructorBio = "Lena produces viral social media content for top creators and global brands with millions of views.",
            instructorStudentsCount = "22,000+ Students",
            reviews = listOf(
                CourseReview("Bamidele T.", 4.8, "1 week ago", "Learned so many practical video editing hacks in under 2 hours.")
            ),
            lessons = listOf(
                Lesson(
                    title = "Cuts & pacing",
                    task = "Write a shot list of 3 shots, each starting with -",
                    startCode = "- ",
                    checkRegexes = listOf("(^|\\n)\\s*-.*\\n\\s*-.*\\n\\s*-"),
                    hint = "Three lines starting with -",
                    videoUri = videoUri(4),
                    duration = "15 mins"
                ),
                Lesson(
                    title = "Sound design",
                    task = "Name a sound effect or music style you would add.",
                    startCode = "",
                    checkRegexes = listOf("whoosh|sfx|music|beat|ambient|impact"),
                    hint = "Try: whoosh, beat, ambient",
                    videoUri = videoUri(0),
                    duration = "12 mins"
                ),
                Lesson(
                    title = "Colour grading",
                    task = "Pick a grade: warm, cool or cinematic.",
                    startCode = "",
                    checkRegexes = listOf("warm|cool|cinematic"),
                    hint = "Type one of the three",
                    videoUri = videoUri(1),
                    duration = "14 mins"
                ),
                Lesson(
                    title = "Export for socials",
                    task = "Write the Reels export size: 1080x1920",
                    startCode = "",
                    checkRegexes = listOf("1080\\s*x\\s*1920"),
                    hint = "Width x height in pixels",
                    videoUri = videoUri(2),
                    duration = "10 mins"
                )
            )
        ),
        Course(
            id = 5,
            icon = "cpu",
            color = "#111114",
            category = "Code",
            title = "AI Tools for Work & Business",
            instructor = "Sam Adeyemi",
            rating = 4.9,
            ratingsCount = "31,005",
            price = "₦10,500",
            isBestseller = true,
            isHighestRated = true,
            level = "Intermediate",
            duration = "7.0 total hours",
            description = "Use AI to write, research and automate the boring parts of your job. Master prompt engineering, AI agents, automated workflows, and executive summaries.",
            requirements = listOf(
                "Basic digital literacy."
            ),
            targetAudience = listOf(
                "Knowledge workers, managers, and business owners.",
                "Anyone looking to boost productivity 10x using AI."
            ),
            instructorTitle = "AI Researcher & Automation Consultant",
            instructorBio = "Sam consults enterprise leadership teams on leveraging generative AI models and intelligent workflows.",
            instructorStudentsCount = "70,000+ Students",
            reviews = listOf(
                CourseReview("Zainab A.", 5.0, "3 days ago", "The best AI productivity course on the market!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Prompting basics",
                    task = "Write a prompt starting with: Act as",
                    startCode = "",
                    checkRegexes = listOf("^\\s*act as\\s+\\w+"),
                    hint = "Begin with Act as a ...",
                    videoUri = videoUri(3),
                    duration = "16 mins"
                ),
                Lesson(
                    title = "Automate boring tasks",
                    task = "Write one task you would automate: Automate ...",
                    startCode = "",
                    checkRegexes = listOf("automate\\s+\\w+"),
                    hint = "Start with the word Automate",
                    videoUri = videoUri(4),
                    duration = "20 mins"
                ),
                Lesson(
                    title = "AI for research",
                    task = "Write one research question ending with ?",
                    startCode = "",
                    checkRegexes = listOf("\\w+.*\\?"),
                    hint = "End with a question mark",
                    videoUri = videoUri(0),
                    duration = "18 mins"
                )
            )
        ),
        Course(
            id = 6,
            icon = "mic",
            color = "#1f8f55",
            category = "Business",
            title = "Public Speaking Confidence",
            instructor = "Grace Bello",
            rating = 4.4,
            ratingsCount = "4,120",
            price = "₦5,900",
            isBestseller = false,
            isHighestRated = false,
            level = "All Levels",
            duration = "3.8 total hours",
            description = "Beat nerves, tell better stories and own any room. Master body language, vocal pacing, structure, and Q&A management.",
            requirements = listOf(
                "Willingness to practice speaking out loud."
            ),
            targetAudience = listOf(
                "Professionals presenting at work or conferences.",
                "Students preparing for speeches and project pitches."
            ),
            instructorTitle = "Keynote Speaker & Communications Coach",
            instructorBio = "Grace is an international keynote speaker who has trained Fortune 500 executives and TEDx speakers.",
            instructorStudentsCount = "15,000+ Students",
            reviews = listOf(
                CourseReview("David O.", 4.6, "2 weeks ago", "Great confidence booster!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Beat the nerves",
                    task = "Write 3 calming steps, each starting with -",
                    startCode = "- ",
                    checkRegexes = listOf("(^|\\n)\\s*-.*\\n\\s*-.*\\n\\s*-"),
                    hint = "Three lines starting with -",
                    videoUri = videoUri(1),
                    duration = "12 mins"
                ),
                Lesson(
                    title = "Story structure",
                    task = "Write a story opening that uses the word Once",
                    startCode = "",
                    checkRegexes = listOf("once"),
                    hint = "Once upon...",
                    videoUri = videoUri(2),
                    duration = "15 mins"
                ),
                Lesson(
                    title = "Body language",
                    task = "Write one tip using eye contact or posture.",
                    startCode = "",
                    checkRegexes = listOf("eye contact|posture"),
                    hint = "Mention eye contact or posture",
                    videoUri = videoUri(3),
                    duration = "14 mins"
                ),
                Lesson(
                    title = "Q&A mastery",
                    task = "Write a Q: and an A: for a question you expect.",
                    startCode = "Q: \nA: ",
                    checkRegexes = listOf("q\\s*:\\s*\\S", "a\\s*:\\s*\\S"),
                    hint = "Fill in after Q: and A:",
                    videoUri = videoUri(4),
                    duration = "16 mins"
                )
            )
        )
    )

    val scenarios = listOf(
        PracticeScenario(
            id = "client-scope",
            title = "Handle a Scope Change",
            persona = "Client (Tunde)",
            topic = "Freelance & Consulting",
            level = "Intermediate",
            color = "#0b6b3a",
            icon = "briefcase",
            opening = "Hi! Thanks for the update. By the way, can we also add a live chat feature and user dashboards before launching next week?",
            prompts = listOf(
                "Thanks for sharing that idea! To make sure we hit next week's launch date, should we scope the live chat for Phase 2?",
                "Got it. What is the priority between launching on time versus adding the dashboard first?"
            ),
            turnCount = 2
        ),
        PracticeScenario(
            id = "job-interview",
            title = "Answer 'Tell me about yourself'",
            persona = "Hiring Manager (Sarah)",
            topic = "Career Development",
            level = "Beginner",
            color = "#111114",
            icon = "account-tie",
            opening = "Welcome! Let's get started. Could you walk me through your background and why you're interested in this role?",
            prompts = listOf(
                "That's a great summary of your past experience. What specific project or achievement are you most proud of?",
                "Impressive! How do you plan to bring those skills into our team's upcoming goals?"
            ),
            turnCount = 2
        ),
        PracticeScenario(
            id = "salary-negotiation",
            title = "Negotiate Project Rate",
            persona = "Marketing Director (David)",
            topic = "Business & Pricing",
            level = "Advanced",
            color = "#1f8f55",
            icon = "cash-multiple",
            opening = "We love your proposal, but our budget for this video campaign is 20% lower than your quote. Can you match our budget?",
            prompts = listOf(
                "I understand your constraints. If we adjust the deliverables slightly (e.g., 2 revisions instead of 4), we can fit your budget.",
                "Sounds fair! Let's update the contract with those agreed revisions."
            ),
            turnCount = 2
        )
    )
}
