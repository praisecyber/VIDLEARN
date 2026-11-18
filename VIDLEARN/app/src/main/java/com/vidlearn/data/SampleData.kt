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
            imageUrl = "https://images.unsplash.com/photo-1507238691740-187a5b1d37b8?w=800&auto=format&fit=crop",
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
            ratingsCount = "24,890",
            price = "₦7,000",
            isBestseller = true,
            isHotAndNew = false,
            level = "Beginner",
            duration = "8.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?w=800&auto=format&fit=crop",
            description = "Master Python syntax, loops, data structures, and build real projects from scratch. Learn clean coding standards, error handling, file automation, and API consumption.",
            requirements = listOf("A computer (Windows, Mac, or Linux)."),
            targetAudience = listOf("Complete beginners with zero programming experience."),
            instructorTitle = "Senior Software Engineer & Python Specialist",
            instructorBio = "Dev has spent over a decade building scalable backend systems.",
            instructorStudentsCount = "120,000+ Students",
            reviews = listOf(
                CourseReview("Chidi K.", 5.0, "3 days ago", "Dev explains complex logic so clearly! Best Python course available.")
            ),
            lessons = listOf(
                Lesson(
                    title = "Variables & types",
                    task = "Create a variable named age set to 25",
                    startCode = "# Write python code below\n",
                    checkRegexes = listOf("age\\s*=\\s*25"),
                    hint = "Write: age = 25",
                    videoUri = videoUri(3),
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
            ratingsCount = "12,100",
            price = "₦9,000",
            isBestseller = false,
            isHotAndNew = false,
            level = "Intermediate",
            duration = "4.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1559526324-4b87b5e36e44?w=800&auto=format&fit=crop",
            description = "Understand personal finance, compound interest, index funds and risk management.",
            requirements = listOf("Basic math skills."),
            targetAudience = listOf("Anyone looking to take control of their personal savings."),
            instructorTitle = "Certified Financial Analyst & Wealth Strategist",
            instructorBio = "Chidi is a CFA charterholder advising young professionals.",
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
            imageUrl = "https://images.unsplash.com/photo-1574717024653-61fd2cf4d44d?w=800&auto=format&fit=crop",
            description = "Cut, grade and export scroll-stopping videos in record time.",
            requirements = listOf("A smartphone or computer with video editing software."),
            targetAudience = listOf("Content creators, YouTubers, and TikTokers."),
            instructorTitle = "Video Producer & Creator Economy Coach",
            instructorBio = "Lena produces viral social media content for top creators.",
            instructorStudentsCount = "22,000+ Students",
            reviews = listOf(
                CourseReview("Bamidele T.", 4.8, "1 week ago", "Learned so many practical video editing hacks.")
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
            imageUrl = "https://images.unsplash.com/photo-1677442136019-21780efad99a?w=800&auto=format&fit=crop",
            description = "Use AI to write, research and automate the boring parts of your job.",
            requirements = listOf("Basic digital literacy."),
            targetAudience = listOf("Knowledge workers, managers, and business owners."),
            instructorTitle = "AI Researcher & Automation Consultant",
            instructorBio = "Sam consults enterprise leadership teams on leveraging generative AI.",
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
            isHotAndNew = false,
            level = "All Levels",
            duration = "3.8 total hours",
            imageUrl = "https://images.unsplash.com/photo-1475721027785-f74eccf877e2?w=800&auto=format&fit=crop",
            description = "Beat nerves, tell better stories and own any room.",
            requirements = listOf("Willingness to practice speaking out loud."),
            targetAudience = listOf("Professionals presenting at work or conferences."),
            instructorTitle = "Keynote Speaker & Communications Coach",
            instructorBio = "Grace is an international keynote speaker who has trained Fortune 500 executives.",
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
                )
            )
        ),
        Course(
            id = 7,
            icon = "code",
            color = "#0b6b3a",
            category = "Code",
            title = "Android Development with Kotlin & Compose",
            instructor = "Praise Cyber",
            rating = 4.9,
            ratingsCount = "14,200",
            price = "₦12,000",
            isBestseller = true,
            isHighestRated = true,
            level = "Intermediate",
            duration = "10.5 total hours",
            imageUrl = "https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?w=800&auto=format&fit=crop",
            description = "Build modern Android applications using Kotlin, Jetpack Compose, StateFlow, and ViewModel.",
            requirements = listOf("Basic programming experience in Kotlin or Java."),
            targetAudience = listOf("Android developers upgrading to Jetpack Compose."),
            instructorTitle = "Lead Mobile Architect",
            instructorBio = "Praise builds enterprise Android applications with modern Jetpack architecture.",
            instructorStudentsCount = "50,000+ Students",
            reviews = listOf(
                CourseReview("Tunde A.", 5.0, "Yesterday", "Outstanding Jetpack Compose explanation!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Composable State",
                    task = "Declare a mutableStateOf boolean set to true",
                    startCode = "",
                    checkRegexes = listOf("mutableStateOf\\s*\\(\\s*true\\s*\\)"),
                    hint = "val state = remember { mutableStateOf(true) }",
                    videoUri = videoUri(2),
                    duration = "15 mins"
                )
            )
        ),
        Course(
            id = 8,
            icon = "dollar-sign",
            color = "#111114",
            category = "Business",
            title = "Digital Marketing & Social Media Ads 2026",
            instructor = "Fatima Alhassan",
            rating = 4.7,
            ratingsCount = "22,400",
            price = "₦8,000",
            isBestseller = true,
            isHotAndNew = true,
            level = "All Levels",
            duration = "8.2 total hours",
            imageUrl = "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=800&auto=format&fit=crop",
            description = "Master Meta Ads, Google Analytics 4, TikTok Marketing, and SEO conversion funnels.",
            requirements = listOf("Basic Internet usage and business goals."),
            targetAudience = listOf("Entrepreneurs, freelancers, and growth marketers."),
            instructorTitle = "Growth Marketing Strategist",
            instructorBio = "Fatima has managed over $5M in ad spend across ecommerce and SaaS.",
            instructorStudentsCount = "65,000+ Students",
            reviews = listOf(
                CourseReview("Victor M.", 4.8, "5 days ago", "Transformed our campaign ROI within two weeks!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Ad Copies",
                    task = "Write a CTA starting with: Buy Now",
                    startCode = "",
                    checkRegexes = listOf("buy now"),
                    hint = "Type: Buy Now to get 20% off",
                    videoUri = videoUri(3),
                    duration = "14 mins"
                )
            )
        ),
        Course(
            id = 9,
            icon = "layout",
            color = "#1f8f55",
            category = "Design",
            title = "UI/UX Figma Design Systems & Prototyping",
            instructor = "Michael Zhang",
            rating = 4.8,
            ratingsCount = "19,800",
            price = "₦9,500",
            isBestseller = true,
            isHighestRated = false,
            level = "Advanced",
            duration = "9.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1581291518633-83b4ebd1d83e?w=800&auto=format&fit=crop",
            description = "Design scalable design systems, auto layout 5.0 components, variables, and high-fidelity prototypes.",
            requirements = listOf("Figma basics recommended."),
            targetAudience = listOf("UI/UX designers scaling enterprise design systems."),
            instructorTitle = "Principal Product Designer",
            instructorBio = "Michael leads design system engineering for global mobile products.",
            instructorStudentsCount = "40,000+ Students",
            reviews = listOf(
                CourseReview("Simi O.", 5.0, "1 week ago", "Figma auto-layout variable section is pure gold!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Design Tokens",
                    task = "Type color code: #0B6B3A",
                    startCode = "",
                    checkRegexes = listOf("#0b6b3a"),
                    hint = "Type #0B6B3A",
                    videoUri = videoUri(4),
                    duration = "18 mins"
                )
            )
        ),
        Course(
            id = 10,
            icon = "dollar-sign",
            color = "#0b6b3a",
            category = "Business",
            title = "Financial Modeling & Valuation Masterclass",
            instructor = "Tariq Mansoor",
            rating = 4.6,
            ratingsCount = "9,300",
            price = "₦11,000",
            isBestseller = false,
            isHotAndNew = false,
            level = "Advanced",
            duration = "7.5 total hours",
            imageUrl = "https://images.unsplash.com/photo-1554224155-8d04cb21cd6c?w=800&auto=format&fit=crop",
            description = "Build 3-statement financial models, discounted cash flow (DCF) valuations, and M&A sensitivity analyses.",
            requirements = listOf("Intermediate Microsoft Excel skills."),
            targetAudience = listOf("Investment banking analysts and corporate finance professionals."),
            instructorTitle = "Ex-Goldman Sachs Vice President",
            instructorBio = "Tariq advised on over $10B in M&A deals.",
            instructorStudentsCount = "18,000+ Students",
            reviews = listOf(
                CourseReview("Kenji Y.", 4.7, "3 days ago", "Extremely practical DCF model walkthrough.")
            ),
            lessons = listOf(
                Lesson(
                    title = "DCF Valuation",
                    task = "Type NPV formula starting with =NPV",
                    startCode = "",
                    checkRegexes = listOf("=npv"),
                    hint = "=NPV(rate, values)",
                    videoUri = videoUri(0),
                    duration = "22 mins"
                )
            )
        ),
        Course(
            id = 11,
            icon = "code",
            color = "#111114",
            category = "Code",
            title = "Full-Stack Web Development: React & Node",
            instructor = "Elena Rostova",
            rating = 4.9,
            ratingsCount = "35,100",
            price = "₦10,000",
            isBestseller = true,
            isHighestRated = true,
            level = "All Levels",
            duration = "12.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1633356122544-f134324a6cee?w=800&auto=format&fit=crop",
            description = "Build full-stack modern web apps with React 19, TypeScript, Node.js, Express, and MongoDB.",
            requirements = listOf("HTML, CSS, JavaScript fundamentals."),
            targetAudience = listOf("Aspiring full-stack software engineers."),
            instructorTitle = "Full-Stack Architect",
            instructorBio = "Elena has built high-concurrency web systems for tech unicorns.",
            instructorStudentsCount = "140,000+ Students",
            reviews = listOf(
                CourseReview("Oscar R.", 5.0, "2 days ago", "The React 19 server components section is super helpful!")
            ),
            lessons = listOf(
                Lesson(
                    title = "React Hooks",
                    task = "Write a useState hook initializing count to 0",
                    startCode = "",
                    checkRegexes = listOf("usestate\\s*\\(\\s*0\\s*\\)"),
                    hint = "const [count, setCount] = useState(0)",
                    videoUri = videoUri(1),
                    duration = "20 mins"
                )
            )
        ),
        Course(
            id = 12,
            icon = "film",
            color = "#1f8f55",
            category = "Creative",
            title = "Photography & Studio Lighting Masterclass",
            instructor = "Julian Vance",
            rating = 4.7,
            ratingsCount = "8,900",
            price = "₦7,500",
            isBestseller = false,
            isHotAndNew = true,
            level = "Beginner",
            duration = "6.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=800&auto=format&fit=crop",
            description = "Master manual camera settings, ISO, aperture, shutter speed, three-point studio lighting, and Lightroom editing.",
            requirements = listOf("Any DSLR or mirrorless camera."),
            targetAudience = listOf("Beginner photographers and portrait artists."),
            instructorTitle = "Fashion & Commercial Photographer",
            instructorBio = "Julian's portrait photography has been featured in Vogue and GQ magazines.",
            instructorStudentsCount = "25,000+ Students",
            reviews = listOf(
                CourseReview("Sonia P.", 4.8, "4 days ago", "My portrait lighting improved instantly!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Exposure Triangle",
                    task = "Type the 3 elements: aperture, shutter, iso",
                    startCode = "",
                    checkRegexes = listOf("aperture.*shutter.*iso"),
                    hint = "Type: aperture shutter iso",
                    videoUri = videoUri(2),
                    duration = "15 mins"
                )
            )
        ),
        Course(
            id = 13,
            icon = "layout",
            color = "#0b6b3a",
            category = "Design",
            title = "Graphic Design Essentials: Photoshop & Illustrator",
            instructor = "Nia Danjuma",
            rating = 4.6,
            ratingsCount = "15,600",
            price = "₦6,900",
            isBestseller = false,
            isHotAndNew = false,
            level = "Beginner",
            duration = "7.2 total hours",
            imageUrl = "https://images.unsplash.com/photo-1626785774573-4b799315345d?w=800&auto=format&fit=crop",
            description = "Create brand logos, social media banners, vector art, and print layouts using Adobe Photoshop and Illustrator.",
            requirements = listOf("Adobe Creative Cloud trial or subscription."),
            targetAudience = listOf("Graphic designers and brand managers."),
            instructorTitle = "Creative Director",
            instructorBio = "Nia runs an independent branding agency servicing global clients.",
            instructorStudentsCount = "38,000+ Students",
            reviews = listOf(
                CourseReview("Kelechi I.", 4.7, "1 week ago", "Excellent logo design techniques!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Vector Pen Tool",
                    task = "Write bezier curve key shortcut: Alt",
                    startCode = "",
                    checkRegexes = listOf("alt"),
                    hint = "Type: Alt",
                    videoUri = videoUri(3),
                    duration = "16 mins"
                )
            )
        ),
        Course(
            id = 14,
            icon = "code",
            color = "#111114",
            category = "Code",
            title = "Data Science & Machine Learning with Python",
            instructor = "Dr. Aris Thorne",
            rating = 4.9,
            ratingsCount = "28,700",
            price = "₦11,500",
            isBestseller = true,
            isHighestRated = true,
            level = "Advanced",
            duration = "14.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=800&auto=format&fit=crop",
            description = "Master NumPy, Pandas, Scikit-Learn, TensorFlow, neural networks, and predictive AI modeling.",
            requirements = listOf("Basic Python knowledge."),
            targetAudience = listOf("Data analysts, software engineers, and AI researchers."),
            instructorTitle = "Chief AI Scientist",
            instructorBio = "Dr. Aris holds a PhD in Machine Learning and leads AI research teams.",
            instructorStudentsCount = "95,000+ Students",
            reviews = listOf(
                CourseReview("Liam W.", 5.0, "3 days ago", "The best machine learning course with real datasets!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Pandas DataFrames",
                    task = "Write import command for pandas as pd",
                    startCode = "",
                    checkRegexes = listOf("import\\s+pandas\\s+as\\s+pd"),
                    hint = "import pandas as pd",
                    videoUri = videoUri(4),
                    duration = "22 mins"
                )
            )
        ),
        Course(
            id = 15,
            icon = "film",
            color = "#1f8f55",
            category = "Creative",
            title = "Music Production in Logic Pro & Ableton Live",
            instructor = "Marcus Beats",
            rating = 4.8,
            ratingsCount = "11,200",
            price = "₦8,900",
            isBestseller = false,
            isHotAndNew = true,
            level = "Intermediate",
            duration = "8.5 total hours",
            imageUrl = "https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?w=800&auto=format&fit=crop",
            description = "Compose, record, arrange, mix, and master radio-ready tracks across Afrobeats, Pop, and Hip-Hop.",
            requirements = listOf("Logic Pro X or Ableton Live DAW."),
            targetAudience = listOf("Music producers, songwriters, and audio engineers."),
            instructorTitle = "Grammy Nominated Audio Engineer",
            instructorBio = "Marcus has produced multi-platinum records for billboard chart-topping artists.",
            instructorStudentsCount = "32,000+ Students",
            reviews = listOf(
                CourseReview("Femi O.", 5.0, "5 days ago", "Mixing techniques increased my beat clarity 10x!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Mixing Kick & Bass",
                    task = "Type EQ frequency cut: 60Hz",
                    startCode = "",
                    checkRegexes = listOf("60\\s*hz"),
                    hint = "Type: 60Hz",
                    videoUri = videoUri(0),
                    duration = "18 mins"
                )
            )
        ),
        Course(
            id = 16,
            icon = "dollar-sign",
            color = "#0b6b3a",
            category = "Business",
            title = "Product Management Fundamentals: Concept to Launch",
            instructor = "Sarah Lin",
            rating = 4.7,
            ratingsCount = "16,400",
            price = "₦9,200",
            isBestseller = true,
            isHighestRated = false,
            level = "All Levels",
            duration = "6.8 total hours",
            imageUrl = "https://images.unsplash.com/photo-1531403009284-440f080d1e12?w=800&auto=format&fit=crop",
            description = "Learn product roadmap strategy, user stories, Agile Scrum sprints, OKRs, and market launch execution.",
            requirements = listOf("No technical background required."),
            targetAudience = listOf("Aspiring Product Managers and Tech Entrepreneurs."),
            instructorTitle = "Director of Product Management",
            instructorBio = "Sarah has led product teams launching apps used by over 50M active users.",
            instructorStudentsCount = "48,000+ Students",
            reviews = listOf(
                CourseReview("Aisha Y.", 4.9, "1 week ago", "Super pragmatic product management framework.")
            ),
            lessons = listOf(
                Lesson(
                    title = "User Stories",
                    task = "Write user story format: As a user I want",
                    startCode = "",
                    checkRegexes = listOf("as a user i want"),
                    hint = "As a user I want...",
                    videoUri = videoUri(1),
                    duration = "16 mins"
                )
            )
        ),
        Course(
            id = 17,
            icon = "code",
            color = "#111114",
            category = "Code",
            title = "Cyber Security & Ethical Hacking Essentials",
            instructor = "Alexandre Dubois",
            rating = 4.8,
            ratingsCount = "21,300",
            price = "₦10,800",
            isBestseller = true,
            isHotAndNew = false,
            level = "Intermediate",
            duration = "11.0 total hours",
            imageUrl = "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=800&auto=format&fit=crop",
            description = "Learn network security, penetration testing, Wireshark packet analysis, and web vulnerability hunting.",
            requirements = listOf("Basic networking concepts."),
            targetAudience = listOf("Security analysts, IT professionals, and ethical hackers."),
            instructorTitle = "Certified Ethical Hacker (CEH) & Security Auditor",
            instructorBio = "Alexandre conducts penetration testing for Fortune 500 financial networks.",
            instructorStudentsCount = "60,000+ Students",
            reviews = listOf(
                CourseReview("Musa H.", 5.0, "2 days ago", "Hands-on Wireshark & Nmap labs are top notch!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Nmap Network Scanning",
                    task = "Type Nmap command: nmap -sV",
                    startCode = "",
                    checkRegexes = listOf("nmap\\s+-sv"),
                    hint = "nmap -sV target",
                    videoUri = videoUri(2),
                    duration = "20 mins"
                )
            )
        ),
        Course(
            id = 18,
            icon = "film",
            color = "#1f8f55",
            category = "Creative",
            title = "Motion Graphics & VFX in After Effects",
            instructor = "Clara Dupont",
            rating = 4.6,
            ratingsCount = "7,800",
            price = "₦8,200",
            isBestseller = false,
            isHotAndNew = true,
            level = "Intermediate",
            duration = "7.8 total hours",
            imageUrl = "https://images.unsplash.com/photo-1550745165-9bc0b252726f?w=800&auto=format&fit=crop",
            description = "Create 2D/3D motion graphics, kinetic typography, logo reveals, and visual effects compositing.",
            requirements = listOf("Adobe After Effects installed."),
            targetAudience = listOf("Animators, video editors, and visual artists."),
            instructorTitle = "Senior Motion Designer",
            instructorBio = "Clara creates motion graphics for commercial television and streaming titles.",
            instructorStudentsCount = "20,000+ Students",
            reviews = listOf(
                CourseReview("Dave C.", 4.7, "4 days ago", "Kinetic typography lesson was amazing!")
            ),
            lessons = listOf(
                Lesson(
                    title = "Keyframe Interpolation",
                    task = "Type Easy Ease shortcut key: F9",
                    startCode = "",
                    checkRegexes = listOf("f9"),
                    hint = "Type: F9",
                    videoUri = videoUri(3),
                    duration = "16 mins"
                )
            )
        ),
        Course(
            id = 19,
            icon = "dollar-sign",
            color = "#0b6b3a",
            category = "Business",
            title = "Copywriting Masterclass: Words That Sell",
            instructor = "Brooke Reynolds",
            rating = 4.9,
            ratingsCount = "17,900",
            price = "₦7,800",
            isBestseller = true,
            isHighestRated = true,
            level = "All Levels",
            duration = "5.5 total hours",
            imageUrl = "https://images.unsplash.com/photo-1455390582262-044cdead277a?w=800&auto=format&fit=crop",
            description = "Write high-converting sales pages, email marketing sequences, landing page headlines, and ad copy.",
            requirements = listOf("Good command of written English."),
            targetAudience = listOf("Copywriters, marketers, and business founders."),
            instructorTitle = "7-Figure Direct Response Copywriter",
            instructorBio = "Brooke's sales letters have generated over $25M in direct response revenue.",
            instructorStudentsCount = "52,000+ Students",
            reviews = listOf(
                CourseReview("Joy N.", 5.0, "3 days ago", "AIDA framework breakdown changed how I write ad headlines!")
            ),
            lessons = listOf(
                Lesson(
                    title = "AIDA Formula",
                    task = "Type 4 steps: Attention, Interest, Desire, Action",
                    startCode = "",
                    checkRegexes = listOf("attention.*interest.*desire.*action"),
                    hint = "Attention Interest Desire Action",
                    videoUri = videoUri(4),
                    duration = "14 mins"
                )
            )
        ),
        Course(
            id = 20,
            icon = "code",
            color = "#111114",
            category = "Code",
            title = "Flutter & Dart Mobile App Development",
            instructor = "Praise Cyber",
            rating = 4.8,
            ratingsCount = "13,500",
            price = "₦11,000",
            isBestseller = true,
            isHotAndNew = true,
            level = "Beginner",
            duration = "11.2 total hours",
            imageUrl = "https://images.unsplash.com/photo-1551650975-87deedd944c3?w=800&auto=format&fit=crop",
            description = "Build beautiful cross-platform iOS and Android mobile apps from a single codebase using Flutter and Dart.",
            requirements = listOf("No prior mobile app development experience required."),
            targetAudience = listOf("Mobile app developers looking to build cross-platform apps."),
            instructorTitle = "Lead Mobile Architect",
            instructorBio = "Praise builds high-performance cross-platform iOS and Android applications.",
            instructorStudentsCount = "50,000+ Students",
            reviews = listOf(
                CourseReview("Hamzat S.", 5.0, "Yesterday", "Best Flutter widget lifecycle explanation!")
            ),
            lessons = listOf(
                Lesson(
                    title = "StatelessWidget",
                    task = "Declare class MyApp extends StatelessWidget",
                    startCode = "",
                    checkRegexes = listOf("class\\s+myapp\\s+extends\\s+statelesswidget"),
                    hint = "class MyApp extends StatelessWidget",
                    videoUri = videoUri(0),
                    duration = "18 mins"
                )
            )
        )
    )
}
