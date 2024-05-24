#!/bin/sh

# Run database migrations
python -c "from app.database import Base, engine; Base.metadata.create_all(bind=engine)"

# Start the application
exec uvicorn app.main:app --host 0.0.0.0 --port 8000