# Article Counting App

Android application to manage store articles: create items, view all items, and update article counts.

## Implemented Features

- Article list screen (main screen) showing:
  - article name
  - article number
  - count status indicator (`green` when count exists, `red` when not counted yet)
- Create article screen:
  - article name input (minimum length: 3)
  - article number input (exactly 7 digits)
  - basic error handling with inline validation messages
- Count article screen:
  - count input for selected article
  - accepted range: `0..999`
  - basic error handling with inline validation messages
- Navigation:
  - `+` from list -> create article
  - article tile tap from list -> count article for that specific item
  - save from create/count returns to list
- State persistence during app runtime:
  - updates from create/count are persisted in repository state and reflected on list immediately

## Architecture

The app follows **Clean Architecture with MVVM**:

- `domain` layer:
  - business models (`Article`)
  - repository contract (`ArticleRepository`)
  - use cases (`GetArticles`, `AddArticle`, `GetArticleById`, `UpdateArticleCount`)
  - validation rules (`ArticleInputValidator`)
- `data` layer:
  - in-memory mocked repository (`InMemoryArticleRepository`)
  - mocked initial data (`MockArticles`)
- `presentation` layer:
  - Compose screens
  - screen-specific ViewModels for state/events
- `navigation`:
  - `Navigation Compose` route graph

This structure keeps UI independent from data source details and makes it easy to replace mocked data with a remote API implementation later by creating a new `ArticleRepository` implementation.

## SOLID and Maintainability

- **S**ingle responsibility: validation logic is isolated from UI code.
- **O**pen/closed: repository abstraction allows adding remote/local implementations without changing use cases.
- **L/I/D**: presentation depends on abstractions (use cases/repository contract), minimizing coupling.
- Use cases keep domain actions explicit and testable.

## Libraries / Technologies

- Kotlin
- Jetpack Compose (UI)
- Navigation Compose (screen navigation)
- AndroidX Lifecycle ViewModel + Kotlin Coroutines/Flow (state management)
- JUnit4 (unit tests)

## Testing

Current tests cover:
- input validation rules
- in-memory repository behavior for creating and updating articles

Run tests:

```bash
./gradlew :app:testDebugUnitTest
```

## Optional Design: Multi-Device Synchronization

See `docs/device-synchronization.md` for:
- architecture choice and rationale
- synchronization data-flow diagram
- conflict resolution strategy for concurrent updates
