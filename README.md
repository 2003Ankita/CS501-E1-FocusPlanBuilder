# Focus Plan Builder

**Name:** Ankita Patra  
**Course:** CS501 E1  
**Assignment:** Individual Coding Assignment 2 — Focus Plan Builder  
**Package:** `com.ankitapatra.focusplanbuilder`
**Repository:** [CS501-E1-FocusPlanBuilder](https://github.com/2003Ankita/CS501-E1-FocusPlanBuilder)

---

## Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [Screenshots](#screenshots)
4. [Running the App](#running-the-app)
5. [How the App Works](#how-the-app-works)
6. [Project Structure](#project-structure)
7. [State and Recomposition](#state-and-recomposition)
8. [Validation and Testing](#validation-and-testing)
9. [Visual Design](#visual-design)
10. [AI Use](#ai-use)

---

## Overview

Focus Plan Builder is a single-screen Android application built with Kotlin,
Jetpack Compose, and Material 3. It helps a user create a simple study plan
based on the subject they want to study and the amount of time they have
available.

The user enters a subject and a duration between 10 and 180 minutes. The app
validates the inputs automatically and enables the **Create plan** button only
when both inputs are valid.

After a plan is created, the app displays:

- the cleaned study subject
- the study duration
- the duration category
- the recommended break time
- a complete study-plan summary

The duration is categorized as **Quick review**, **Focused session**, or
**Extended session**, with a corresponding recommended break.

The application uses a fully declarative Jetpack Compose interface without
XML layouts, Fragments, or legacy Android Views.
---

---

## Features

### Assignment Requirements

- Single-screen Android application built with Kotlin and Jetpack Compose.
- Material 3 components used throughout the interface.
- Subject input validated using `isNotBlank()`.
- Duration entered as text and safely parsed using `toIntOrNull()`.
- Valid study duration restricted to **10–180 minutes**.
- **Create plan** button enabled only when both inputs are valid.
- `durationCategory(minutes)` classifies the study session as:
    - **Quick review:** 10–29 minutes
    - **Focused session:** 30–60 minutes
    - **Extended session:** 61–180 minutes
- `recommendedBreak(minutes)` recommends a **5, 10, or 15 minute** break.
- `FocusPlan` data class stores the subject, duration, category, and break duration.
- Generated result displays the subject, duration, category, recommended break, and a complete study-plan summary.
- Editing either input after creating a plan immediately removes the previous result.
- `rememberSaveable` preserves the subject and duration inputs during configuration changes such as screen rotation.
- Layout uses Compose `Column`, `Row`, `Spacer`, and `Card` components.

### Additional UI Features

- Quick-pick buttons for **15, 25, 45, and 60 minutes**.
- Live preview of the duration category and recommended break.
- Validation feedback when the duration is invalid.
- Scrollable interface that works in portrait and landscape orientations.
- Custom purple, peach, green, and pink Material 3 color palette.
- Soft gradient application background.
- Rounded input, action, and result cards.
- Footer message: **“Small steps. Big progress.”**

---

## Screenshots

The following screenshots demonstrate the main study-plan categories, input
validation behavior, and state preservation during screen rotation.

### Study Plan Categories

| Quick Review — 10 Minutes | Focused Session — 60 Minutes | Extended Session — 61 Minutes |
|---|---|---|
| ![Quick Review](screenshots/quick_review.png) | ![Focused Session](screenshots/focus_plan_builder.png) | ![Extended Session](screenshots/extended_session.png) |
| **Quick review** with a **5-minute** recommended break. | **Focused session** with a **10-minute** recommended break. | **Extended session** with a **15-minute** recommended break. |

These scenarios demonstrate the three duration categories produced by
`durationCategory()` and the corresponding break values returned by
`recommendedBreak()`.

### Input Validation

| Non-Numeric Input | Out-of-Range Input |
|---|---|
| ![Invalid Duration](screenshots/invalid_duration.png) | ![Out of Range](screenshots/out_of_range.png) |
| Entering `abc` keeps **Create plan** disabled and displays validation feedback without crashing the app. | Entering `181` keeps **Create plan** disabled because the valid range ends at 180 minutes. |

### Screen Rotation

| Landscape Orientation |
|---|
| ![Landscape](screenshots/landscape.png) |
| The subject and duration inputs remain available after rotation because they are stored using `rememberSaveable`. |

---

---

## Running the App

### Requirements

- Android Studio
- Android SDK 24 or higher
- Kotlin and Jetpack Compose support
- Android emulator or physical Android device

### Steps

1. Clone the repository.
2. Open the `Focus_Plan_Builder` project in Android Studio.
3. Allow Gradle to sync and download the required dependencies.
4. Start an Android emulator or connect an Android device.
5. Select the `app` run configuration.
6. Click **Run ▶**.
7. Enter a study subject.
8. Enter a duration between **10 and 180 minutes**.
9. Click **Create plan** to generate the study plan.

---

## How the App Works

The application separates state management from presentation.

`FocusPlanRoute` owns the subject, duration text, generated plan, validation,
and plan-creation logic. `FocusPlanScreen` receives the current values and
callbacks and is responsible for displaying the interface.

The duration begins as text because it comes directly from an editable text
field. It is converted safely when needed:

```kotlin
val minutes: Int? = minutesText.toIntOrNull()
```

The enabled state of the **Create plan** button is derived directly from the
current inputs:

```kotlin
val canCreatePlan =
    subject.isNotBlank() &&
        minutes != null &&
        minutes in 10..180
```

When the user selects **Create plan**, the application creates a `FocusPlan`
using the trimmed subject, validated duration, calculated duration category,
and recommended break.

### Duration Rules

| Duration | Category | Recommended Break |
|---|---|---|
| 10–29 minutes | Quick review | 5 minutes |
| 30–60 minutes | Focused session | 10 minutes |
| 61–180 minutes | Extended session | 15 minutes |

Editing either the subject or duration after a plan has been created clears
the previous result so that the displayed plan always corresponds to the
current input.

---
## Project Structure

The application is organized into separate model, presentation, component,
and theme files to keep state management, business logic, and UI concerns
clearly separated.

```text
app/src/main/java/com/ankitapatra/focusplanbuilder/
├── MainActivity.kt
├── model/
│   ├── FocusPlan.kt
│   ├── DurationCategory.kt
│   └── BreakRecommendation.kt
├── presentation/
│   ├── FocusPlanRoute.kt
│   ├── FocusPlanScreen.kt
│   └── components/
│       ├── FocusPlanHeader.kt
│       ├── PlanInputCard.kt
│       ├── QuickPickButtons.kt
│       ├── CreatePlanButton.kt
│       └── FocusPlanResultCard.kt
└── ui/
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt

app/src/test/java/com/ankitapatra/focusplanbuilder/
├── DurationCategoryTest.kt
├── RecommendedBreakTest.kt
└── FocusPlanTest.kt
```

`model` contains the `FocusPlan` data model and reusable duration and break
rules. `presentation` contains the route that owns application state and the
screen that arranges the UI. Reusable Compose elements are separated into
`presentation/components`, while Material 3 colors and styling remain in
`ui/theme`.

Unit tests are kept under `app/src/test` and verify the duration-category
boundaries, recommended break values, and `FocusPlan` data model.

---

## State and Recomposition

`FocusPlanRoute` owns the application state, including `subject`,
`minutesText`, and the generated `plan`. It also performs validation and
creates the `FocusPlan`. `FocusPlanScreen` is responsible for presenting the
UI and sends user actions back to the route through callbacks. This keeps
state management separate from presentation.

The text-field values are stored as `String` because text input can
temporarily contain values that cannot be represented as an `Int`, such as
an empty field or `abc`. For calculations, `minutesText` is converted using
`toIntOrNull()`. This is safer than `toInt()` because invalid input returns
`null` rather than throwing an exception and crashing the application.

`canCreatePlan` is derived from the current subject and duration instead of
being stored as separate mutable state. When either input changes, Compose
observes the state change and recomposes the affected UI. Validation is
recalculated automatically, so the **Create plan** button immediately becomes
enabled or disabled.

`rememberSaveable` is used for `subject` and `minutesText`, allowing these
values to survive Activity recreation such as screen rotation. The generated
plan itself uses `remember`, while editing either input clears the old plan
by setting it to `null`.

---

## Validation and Testing

The application was tested using both automated unit tests and manual testing.

### Automated Unit Tests

Three unit-test files verify the reusable model and business-logic components:

- `DurationCategoryTest.kt` verifies the category boundaries, including 9, 10,
  29, 30, 60, 61, and 180 minutes.
- `RecommendedBreakTest.kt` verifies the 5-, 10-, and 15-minute break rules and
  the invalid-duration case.
- `FocusPlanTest.kt` verifies that the `FocusPlan` data class stores the subject,
  duration, category, and recommended break correctly.

All automated unit tests pass successfully.

### Manual Testing

The application was also manually tested using the required assignment test cases.

| Subject | Duration | Expected Result |
|---|---:|---|
| Blank | 25 | Create plan disabled |
| Kotlin | Blank | Create plan disabled |
| Kotlin | abc | Create plan disabled; no crash |
| Kotlin | 9 | Create plan disabled |
| Kotlin | 10 | Quick review; 5-minute break |
| Kotlin | 29 | Quick review; 5-minute break |
| Kotlin | 30 | Focused session; 10-minute break |
| Kotlin | 60 | Focused session; 10-minute break |
| Kotlin | 61 | Extended session; 15-minute break |
| Kotlin | 180 | Extended session; 15-minute break |
| Kotlin | 181 | Create plan disabled |

### Additional Manual Test Scenarios

In addition to the required test cases above, the following behaviors were
manually verified:

- Erasing the duration after entering a value does not crash the application.
- Entering non-numeric input such as `abc` does not crash the application.
- A subject containing only whitespace is treated as blank.
- Subject and duration inputs remain available after screen rotation.
- Editing the subject after creating a plan removes the previous result.
- Editing the duration after creating a plan removes the previous result.
- The **Create plan** button automatically updates when the validity of either
  input changes.
- Quick-pick buttons for **15, 25, 45, and 60 minutes** correctly update the
  duration field.
- The live preview displays the corresponding session category and recommended
  break for a valid duration.
- The interface remains scrollable and usable in landscape orientation.

### Boundary Coverage

The required tests verify the boundaries between all three duration
categories:

- **10 and 29 minutes** → Quick review with a 5-minute break.
- **30 and 60 minutes** → Focused session with a 10-minute break.
- **61 and 180 minutes** → Extended session with a 15-minute break.
- Values below **10** or above **180** keep **Create plan** disabled.

The screenshots above provide visual examples of Quick review, Focused
session, Extended session, non-numeric input, out-of-range input, and screen
rotation.

---

## Visual Design

The application uses a custom Material 3 interface with purple as the primary
color and soft peach, pink, and green accent colors. A subtle vertical
gradient is used for the screen background.

The input area uses a warm peach card, while the generated focus plan uses a
green result card to clearly distinguish the output from the input section.
The quick-pick duration buttons use pastel colors, while the primary
**Create plan** action uses purple.

Rounded cards, spacing, centered headings, and the
**“Small steps. Big progress.”** footer were added to make the single-screen
interface clear and visually organized.

---

## AI Use

Generative AI (ChatGPT by OpenAI) was used to assist with identifying an
appropriate Compose structure, UI refinement,review of the required test cases, and README organization. AI-generated suggestions that were retained were
reviewed, implemented, and manually tested. The final application behavior
and assignment requirements were verified against the implemented code.