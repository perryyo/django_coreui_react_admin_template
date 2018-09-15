from .default import *

# SECURITY WARNING: don't run with debug turned on in production!
DEBUG = True

STATICFILES_DIRS.append(
    os.path.join(BASE_DIR, os.pardir, 'frontend', 'public')
)
STATICFILES_DIRS.append(
    os.path.join(BASE_DIR, os.pardir, 'frontend', 'build')
)

