Dev Setup (Docker) — React + Spring Boot + PostgreSQL0) PrerequisitesWindowsDocker Desktop installed and running.Git installed.(Recommended) IntelliJ IDEA for Backend / VS Code for Frontend.If using WSL2: Enable WSL Integration in Docker Desktop → Settings → Resources.macOS / LinuxDocker Desktop / Docker Engine installed.Ensure your user is in the docker group (Linux).Note: You do not need Java (JDK) or Node.js installed locally; all dependencies and runtimes are managed within the Docker containers.1) Project StructureFor the setup to work, your local directory must follow this hierarchy:PlaintextErasmus-Hub/
├── docker-compose.yml     # Orchestration file
├── .env                   # Local config (Created from .env.example)
├── backend/               # Java 25 / Spring Boot source
└── frontend/              # React / TypeScript source
2) Configure Environment VariablesDocker Compose relies on the .env file in the root directory to map ports and database credentials.Windows (PowerShell)PowerShellCopy-Item .env.example .env
macOS / Linux (Bash)Bashcp .env.example .env
Mandatory Check: Open .env and ensure FRONTEND_PORT=3000 is defined.3) Mandatory Vite Configuration (Frontend)To access the React app from your browser (localhost:3000), the Vite server must listen on all network interfaces. Verify that frontend/vite.config.ts includes:TypeScriptexport default defineConfig({
  server: {
    host: '0.0.0.0',      // Exposes the server to the Docker network
    port: 3000,
    strictPort: true,
    watch: {
      usePolling: true,   // Enables Hot Reloading on Windows/WSL2
    },
  },
})
4) Start the StackRun this command from the root of the repository (Erasmus-Hub/):PowerShelldocker compose up -d --build
First-run Expectations:Database: Initializes the erasmus_db_data volume.Backend: Maven downloads dependencies (this may take several minutes).Frontend: Container runs npm install automatically before starting.5) Service Access & NetworkingContainers communicate using service names as hostnames.ServiceAccess URL (Your Browser)Internal URL (For API calls)Frontendhttp://localhost:3000http://frontend:3000Backend APIhttp://localhost:8080http://backend:8080PostgreSQLlocalhost:5432jdbc:postgresql://db:5432/6) Useful CommandsMonitor Logs (Crucial for Debugging):PowerShell# See everything
docker compose logs -f

# Specific services
docker compose logs -f backend
docker compose logs -f frontend
Stop the Environment:PowerShelldocker compose down
Full Reset (Clean volumes/cache):Use this if the database is corrupted or dependencies are broken:PowerShelldocker compose down -v
docker compose up --build
7) TroubleshootingERR_CONNECTION_REFUSED: Usually means npm install is still running or the Vite host: '0.0.0.0' setting is missing. Check docker compose logs -f frontend.Database Connection Failed: The backend might start faster than the DB. The setup includes a healthcheck, but if it fails, simply run docker compose restart backend.Port Conflict: If port 8080, 3000, or 5432 is occupied, change the value in your .env file and restart.8) Pre-push ChecklistClean Code: Do not upload backend/target/ or frontend/node_modules/.Environment Privacy: Ensure .env is NOT committed to Git.Build Sanity: Always verify your changes build successfully via docker compose up --build before pushing.